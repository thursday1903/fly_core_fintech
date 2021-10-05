package springboot.bussiness;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.connection.RedisConnection;
import springboot.cache.RedisDataConfig;
import springboot.config.MainConfig;
import springboot.hbn.entities.Branch;
import springboot.hbn.entities.TblBanks;
import springboot.hbn.entities.TblBorrower;
import springboot.hbn.entities.TblBranchBank;
import springboot.hbn.entities.TblLoanBill;
import springboot.hbn.entities.TblLoanReqDetail;
import springboot.hbn.entities.TblLoanRequest;
import springboot.hbn.home.TblBankHome;
import springboot.hbn.home.TblBorrowerHome;
import springboot.hbn.home.TblBranchBankHome;
import springboot.hbn.home.TblLoanBillHome;
import springboot.hbn.home.TblLoanReqDetailHome;
import springboot.hbn.home.TblLoanRequestHome;
import springboot.logs.Logs;
import springboot.transport.message.IndebtRemind;
import springboot.transport.message.NotifyMessageTransfer;
import springboot.utils.Commons;
import springboot.utils.DateConvert;
import springboot.utils.GsonUltilities;

public class BussinessLoanProcess {
	/**
	 * static Singleton instance.
	 */
	private static volatile BussinessLoanProcess instance;

	final static Logs LOGGER = new Logs(BussinessLoanProcess.class);
	TblLoanBillHome tblLoanBillHome = new TblLoanBillHome();
	TblLoanRequestHome tblLoanRequestHome = new TblLoanRequestHome();
	TblLoanReqDetailHome tblLoanRequestDetailHome = new TblLoanReqDetailHome();
	TblBorrowerHome tblBorrowerHome = new TblBorrowerHome();
	TblBranchBankHome tblBranchBankHome = new TblBranchBankHome();
	TblBankHome tblBankHome = new TblBankHome();
	static Hashtable<Integer, TblBanks> hashBank = new Hashtable<Integer, TblBanks>();
	static Hashtable<Integer, Branch> hashBranch = new Hashtable<Integer, Branch>();
	static DecimalFormat decimalFormat = new DecimalFormat("###,###.###");


	static String EMAIL_TEMPLATE_INDEBT_REMIND = null;// NỘI DUNG NHẮC NỢ
	static String EMAIL_TEMPLATE_INDEBT_OVERDUE = null;// NỘI DUNG CẢNH BÁO QUÁ HẠN
	final String EMAIL_SUBJECT = "Thong bao";
	final String QUEUE_INDEBT_REMIND = "QUEUE_INDEBT_REMIND";
	final String QUEUE_INDEBT_REMIND_LOGS = "QUEUE_INDEBT_REMIND_LOGS";
	final String QUEUE_SEND_EMAIL = "QUEUE_EMAIL_INDEB_RMD";
	final Integer SUBSTRACT_DAY_RESULT_SHOULD_REMIND = 5;// Số ngày hiện tại - daymustpay nếu bằng sẽ cảnh bao
	final String LIST_CONFIG_HTML_BANK = "<p style=\"text-align:justify\"><span style=\"font-size:8pt\"><span style=\"font-family:&quot;Times New Roman&quot;,serif\"><span style=\"font-size:11.0pt\">T&ecirc;n t&agrave;i khoản: <span >[bank_acc_name]</span></span></span></span></p>\r\n"
			+ "<p style=\"text-align:justify\"><span style=\"font-size:8pt\"><span style=\"font-family:&quot;Times New Roman&quot;,serif\"><strong><span style=\"font-size:11.0pt\"><span >Số t&agrave;i khoản: [bank_acc_no]&nbsp;tại Ng&acirc;n h&agrave;ng [bank_name]&nbsp;&ndash; Chi nh&aacute;nh [bank_branch]</span></span></strong></span></span></p>";
	
	/**
	 * Private constructor for singleton.
	 */
	private BussinessLoanProcess() {
		try {
			List<Object> lstList = tblBankHome.listAllObject(new TblBanks());
			for (Object object : lstList) {
				TblBanks tblBanks = (TblBanks)object;
				hashBank.put(Integer.parseInt(tblBanks.getRowId()), tblBanks);
			}
			
			List<Object> lstBranch = tblBankHome.listAllObject(new Branch());
			for (Object object : lstBranch) {
				Branch tblBanks = (Branch)object;
				hashBranch.put(tblBanks.getRowId(), tblBanks);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EMAIL_TEMPLATE_INDEBT_REMIND = getTemplateRemind();
		EMAIL_TEMPLATE_INDEBT_OVERDUE = getTemplateWarning();
	}

	/**
	 * Return a singleton instance of BussinessLoanProcess.
	 */
	public static BussinessLoanProcess getInstance() {
		// Double lock for thread safety.
		if (instance == null) {
			synchronized (BussinessLoanProcess.class) {
				if (instance == null) {
					instance = new BussinessLoanProcess();
				}
			}
		}
		return instance;
	}

	public void composeNotifyEmailAndPushQueue() {
		RedisConnection jedis = null;
		LOGGER.info("scan queue:"+QUEUE_INDEBT_REMIND);
		try {
			jedis = RedisDataConfig.redisConnectionFactory().getConnection();

			byte[] redisData = jedis.lPop(QUEUE_INDEBT_REMIND.getBytes());
			while (redisData != null) {
				IndebtRemind indebtRemind = (IndebtRemind) GsonUltilities.fromJson(new String(redisData),
						IndebtRemind.class);
				createNotify(indebtRemind);
				
				redisData = jedis.lPop(QUEUE_INDEBT_REMIND.getBytes());
				if(MainConfig.QUEUE_LOGS_FLAG)
				{
					jedis.rPush(QUEUE_INDEBT_REMIND_LOGS.getBytes(), redisData);
				}
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			LOGGER.info("Queue:"+QUEUE_INDEBT_REMIND+",empty");

		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.fatal("composeNotifyEmailAndPushQueue",e);
		} finally {
			RedisDataConfig.releseConnection(jedis);
		}
	}

//	public String createNotify(String loanCode, Integer loanId, Integer billId) {
//		StringBuffer stringLogStream = new StringBuffer();
//		String emailContent = null;
//		try {
//			stringLogStream.append("loan_id:" + loanId + "\r\n");
//			TblLoanRequest loanRequest = (TblLoanRequest) tblLoanBillHome.findById(loanId, new TblLoanRequest());
//			if (loanRequest != null) {
//				TblLoanReqDetail tblLoanReqDetail = (TblLoanReqDetail) tblLoanBillHome.findById(loanRequest.getLoanId(),
//						new TblLoanReqDetail());
//				if (tblLoanReqDetail != null) {
//					stringLogStream.append("bill_id:" + billId + "\r\n");
//					TblLoanBill tblLoanBill = (TblLoanBill) tblLoanBillHome.findById(billId, new TblLoanBill());
//
//					if (loanBillIsOverDue(tblLoanBill))
//						emailContent = new String(EMAIL_TEMPLATE_INDEBT_OVERDUE);
//					else
//						emailContent = new String(EMAIL_TEMPLATE_INDEBT_REMIND);
//
//					emailContent = emailContent.replace("[borrower_fullname]", tblLoanReqDetail.getBorrowerFullname())
//							.replace("[loan_code]", loanRequest.getLoanCode().replace("[bill_index]",
//									String.format("%02d", tblLoanBill.getBillIndex())));
//					stringLogStream.append("complete compose content:" + emailContent + "\r\n");
//				} else {
//					LOGGER.info("không thấy thông tin loan_request_detail:" + loanId);
//				}
//			} else {
//				LOGGER.info("không thấy thông tin loan_id:" + loanId);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			LOGGER.fatal("FATAL:" + loanCode + "=>" + stringLogStream.toString());
//		}
//		return emailContent;
//	}

	/**
	 * @param tblLoanBill
	 * @return true: khoản vay bị quá hạn, khoản vay nhắc thanh toán
	 * @throws Exception
	 */
	public Boolean loanBillIsOverDue(TblLoanBill tblLoanBill) throws Exception {
		try {
			Date dayMustPay = DateConvert.stringToDate(tblLoanBill.getDayMustPay() + "", "yyyyMMdd");
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			long diffInMillies = Math.abs(calendar.getTime().getTime() - dayMustPay.getTime());
			long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			if (diff < 0) {
				if (Math.abs(diff) <= SUBSTRACT_DAY_RESULT_SHOULD_REMIND)
					return false;
			}
			return true;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

	}

	public static void main(String[] args) {
		
		System.out.println(String.format("%02d", 1));
//		System.exit(2);;
		String json = "{\"loan_code\":\"BHVN.24.10224\",\"loan_id\":\"174\",\"bill_id\":\"1785\"}";
		IndebtRemind indebtRemind = (IndebtRemind)GsonUltilities.fromJson(json, IndebtRemind.class);
		BussinessLoanProcess.getInstance().createNotify(indebtRemind);
//		TblLoanRequest tblLoanRequest = new TblLoanRequest();
//		tblLoanRequest.setBranchId(24);;
//		getInstance().getLoanBankPaymentConfig(tblLoanRequest);
	}
	
	public String getLoanBankPaymentConfig(TblLoanRequest tblLoanRequest)
	{
		StringBuffer stringBuffer = new StringBuffer();
		try {
			 List<Object> lstBranchBank = tblBranchBankHome.getListBank(tblLoanRequest.getBranchId());
			 if(lstBranchBank.size()>0)
			 {
				 for (Object object : lstBranchBank) {
					 String singleLine = new String(LIST_CONFIG_HTML_BANK);
					TblBranchBank tblBranchBank = (TblBranchBank)object;
					TblBanks tblBanks = hashBank.get(tblBranchBank.getBankId());
					Branch branch = hashBranch.get(tblLoanRequest.getBranchId());
					singleLine = singleLine.replace("[bank_acc_name]", (branch.getBankAccountName()==null?"N/A":branch.getBankAccountName()))
					.replace("[bank_acc_no]", (tblBranchBank.getBankAccount()==null?"N/A":tblBranchBank.getBankAccount()))
					.replace("[bank_name]", (tblBanks.getBankName()==null?"N/A":tblBanks.getBankName()))
					.replace("[bank_branch]", (tblBranchBank.getBankBranch()==null?"N/A":tblBranchBank.getBankBranch()));
					stringBuffer.append(singleLine +"</br>");
				 }
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stringBuffer.toString();
	}
	
	public String createNotify(IndebtRemind indebtRemind) {
		StringBuffer stringLogStream = new StringBuffer();
		String emailContent = null;
		Boolean composeContent = false;
		try {
			stringLogStream.append("loan_id:" + indebtRemind.getBill_id() + "\r\n");
			TblLoanRequest loanRequest = (TblLoanRequest) tblLoanBillHome.findById(indebtRemind.getLoan_id(),
					new TblLoanRequest());
			if (loanRequest != null) {
				TblLoanReqDetail tblLoanReqDetailTmp = new TblLoanReqDetail();
				tblLoanReqDetailTmp.setLoanId(loanRequest.getLoanId());
				TblLoanReqDetail tblLoanReqDetail = (TblLoanReqDetail) tblLoanRequestDetailHome.findDetailByLoanId(tblLoanReqDetailTmp);
				if (tblLoanReqDetail != null) {
					stringLogStream.append("bill_id:" + indebtRemind.getBill_id() + "\r\n");
					TblLoanBill tblLoanBill = (TblLoanBill) tblLoanBillHome.findById(indebtRemind.getBill_id(),
							new TblLoanBill());
					if (loanBillIsOverDue(tblLoanBill))
					{
						stringLogStream.append("Khoan vay qua han\r\n" );
						emailContent = new String(EMAIL_TEMPLATE_INDEBT_OVERDUE);
					}
					else
					{
						stringLogStream.append("Khoan vay nhac' sap den han\r\n" );
						emailContent = new String(EMAIL_TEMPLATE_INDEBT_REMIND);
					}
					String billIndex = String.format("%02d", tblLoanBill.getBillIndex());
					stringLogStream.append("bill_index:"+ billIndex);
					Integer overDueFee = tblLoanBill.getOverDueFee()==null?0:tblLoanBill.getOverDueFee();
					String totalPay = decimalFormat.format((tblLoanBill.getTotalOnAMonth()==null?0:tblLoanBill.getTotalOnAMonth().longValue())+overDueFee);
					String listBank = getLoanBankPaymentConfig(loanRequest);
					
					Calendar calendar = Calendar.getInstance();
					int dayOfReport = calendar.getTime().getDate();
					int monthOfReport = calendar.getTime().getMonth()+1;
					int yearOfReport = calendar.getTime().getYear()+1900;
					Branch branch = hashBranch.get(loanRequest.getBranchId());
					
					emailContent = emailContent.replace("[borrower_fullname]", tblLoanReqDetail.getBorrowerFullname())
							.replace("[loan_code]", loanRequest.getLoanCode())
							.replace("[bill_index]",billIndex)
							.replace("[amount_must_pay]", totalPay)
							.replace("[bank_list]", listBank)
							.replace("[dd]", String.format("%02d", dayOfReport))
							.replace("[mm]", String.format("%02d", monthOfReport))
							.replace("[yyyy]", yearOfReport+"")
							.replace("[branch_name]", branch.getBranchName());
							
					stringLogStream.append("complete compose content:" + emailContent + "\r\n");
					composeContent = true;
					//Tìm borrowerid:
					String borrowerIdNumber = tblLoanReqDetail.getBorrowerId();
					//Query sang borrower để lấy thông tin người nhận.
					TblBorrower tblBorrower = new TblBorrower();
					tblBorrower.setIdNumber(borrowerIdNumber);
					List<Object> lstBorrower = tblBorrowerHome.findBorrowerByIdNumber(tblBorrower);
					if (lstBorrower != null) {
						tblBorrower = (TblBorrower)lstBorrower.get(0);
						stringLogStream.append("tim thay thong tin email va sdl cua acc co id number:"+ borrowerIdNumber+"\r\n");
						stringLogStream.append("email:"+tblBorrower.getBorrowerEmail());
						stringLogStream.append("phone:"+tblBorrower.getBorrowerMobile());
						indebtRemind.setEmailContent(emailContent);
						indebtRemind.setEmailSubject(EMAIL_SUBJECT);
						indebtRemind.setReceiveEmail(tblBorrower.getBorrowerEmail());
						indebtRemind.setReceivePhone(tblBorrower.getBorrowerMobile());
					} else {
						stringLogStream.append("khong thay thong tin email va sdl cua acc co id number:"+ borrowerIdNumber);
					}
				} else {
					stringLogStream.append("không thấy thông tin loan_request_detail:" + indebtRemind.getBill_id()+"\r\n");
					LOGGER.info("không thấy thông tin loan_request_detail:" + indebtRemind.getBill_id());
				}
			} else {
				stringLogStream.append("không thấy thông tin loan_id:" + indebtRemind.getLoan_id()+"\r\n");
				LOGGER.info("không thấy thông tin loan_id:" + indebtRemind.getLoan_id());
			}
		} catch (Exception e) {
			// TODO: handle exception
			stringLogStream.append("FATAL:" + indebtRemind.getLoan_code()+ e.getLocalizedMessage());
			LOGGER.fatal("FATAL:" + indebtRemind.getLoan_code() + "=>" + stringLogStream.toString());
		}
		finally {
			LOGGER.info("kq process notify:"+stringLogStream.toString());
		}
		if (composeContent) {
			String notifyData = packNotification(indebtRemind);
			pushTransactionToRedisQueue(notifyData, QUEUE_SEND_EMAIL);
		} else {
			LOGGER.info("composeContent fail:" + indebtRemind.toString());
		}
		return emailContent;
	}

	
	
	private Boolean pushTransactionToRedisQueue(String data, String queueName) {
		RedisConnection conn = null;
		try {
			conn = RedisDataConfig.redisConnectionFactory().getConnection();
			conn.rPush(queueName.getBytes(), data.getBytes("utf-8"));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.fatal("pushTransactionToRedisQueue", e);
		} finally {
			RedisDataConfig.releseConnection(conn);
		}
		return false;
	}

	public String packNotification(IndebtRemind indebtRemind) {
//		String subject, String content, Integer message_type, Boolean is_html,
//		String receive_email_expect, String receive_sms_expect, String receive_chat_id_expect, String service_code,
//		String sub_service_code, String receive_email_expect_cc
		NotifyMessageTransfer notifyMessageTransfer = new NotifyMessageTransfer(indebtRemind.getEmailSubject(),
				indebtRemind.getEmailContent(), 1, true, indebtRemind.getReceiveEmail(), null, null, null, null, null);

		return GsonUltilities.toJson(notifyMessageTransfer);
	}

	public String getTemplateRemind() {
		StringBuffer sb = new StringBuffer(); // constructs a string buffer with no characters
		try {
			File file = new File("./config/file_templates/TEMPLATE_REMIND.html"); // creates a new file instance
			FileReader fr = new FileReader(file); // reads the file
			BufferedReader br = new BufferedReader(fr); // creates a buffering character input stream

			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line); // appends line to string buffer
				sb.append("\n"); // line feed
			}
			fr.close(); // closes the stream and release the resources
			System.out.println("Contents of File: ");
			System.out.println(sb.toString()); // returns a string that textually represents the object
			LOGGER.info("doc du lieu file success:" + sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.fatal("getSettleMailTemplate", e);
		}
		return sb.toString();
	}

	/**
	 * Lấy template cảnh báo nợ quá hạn
	 * 
	 * @return
	 */
	public String getTemplateWarning() {
		StringBuffer sb = new StringBuffer(); // constructs a string buffer with no characters
		try {
			File file = new File("./config/file_templates/TEMPLATE_WARNING_OVERDUE.html"); // creates a new file instance
			FileReader fr = new FileReader(file); // reads the file
			BufferedReader br = new BufferedReader(fr); // creates a buffering character input stream

			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line); // appends line to string buffer
				sb.append("\n"); // line feed
			}
			fr.close(); // closes the stream and release the resources
			System.out.println("Contents of File: ");
			System.out.println(sb.toString()); // returns a string that textually represents the object
			LOGGER.info("doc du lieu file success:" + sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.fatal("getSettleMailTemplate", e);
		}
		return sb.toString();
	}
}
