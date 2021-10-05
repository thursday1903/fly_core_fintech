package springboot.config;

public class Constant {

	public Constant() {
		// TODO Auto-generated constructor stub
	}

	public final static String PROCESSING_TOPUP = "10000";
	public final static String PROCESSING_CHECKTRANS = "10001";

	public final static String PROVIDER_AVAIL = "A";
	public final static String PROVIDER_INACTIVE = "I";

	public final static String TRN_SUCCESS = "00";
	public final static String TRN_ERROR_EXCEPTION = "-1";
	public final static String TRN_INVALID_PARTNER = "01";
	public final static String TRN_INVALID_PROVIDER = "02";
	public final static String TRN_INSERT_FAIL = "03";
	public final static String TRN_PARSE_REQUEST_FAIL = "04";
	public final static String TRN_INVALID_REQUEST_ID = "05";
	public final static String TRN_WRONG_SERIAL = "06";
	public final static String TRN_WRONG_PIN = "07";
	public final static String TRN_PROVIDER_BE_LOCKED = "08";
	public final static String TRN_FAIL = "09";
	public final static String TRN_FAIL_CARD_EXPIRE = "10";
	public final static String TRN_TRANSACTION_FAIL_FROM_TEL = "18";
	public final static String TRN_CARD_IS_USED = "24";
	public final static String TRN_REQUEST_ID_EXISTED = "31";
	public final static String TRN_DONOT_EXISTED = "29";
	public final static String TRN_INVALID_CARD_PRINT_AMOUNT = "30";
	public final static String TRN_REQUEST_PENDING = "99";
	public final static String TRN_CARD_SERIAL_IS_PENDING = "32";// THE NAY DANG
																	// PENDING,
																	// GOI LAI
																	// HAM QUERY
	public final static String TRN_FAIL_WHEN_CONNECT_TIMEOUT = "33";
	public final static String TRN_UNPROCESS_BECAUSE_NO_ORDER_WAIT = "34";
	public final static String TRN_UNPROCESS_ENQUEUE_FAIL = "35";
	public final static String TRN_TRANSACTION_FAIL_TARGET_INVALID = "36";
	public final static String TRN_REQUEST_FAIL_CARD_IS_NOTPROCESS = "37";// khong
	// nap
	// dc
	// do
	// sim
	// bi
	// khoa
	public final static String TRN_REQUEST_FAIL_INVALID_CAPTCHA = "38";// khong
	public final static String TRN_REQUEST_FAIL_AMOUNT_OVER_BUDGET = "39";// khong
	public final static String TRN_REQUEST_FAIL_INVALID_ID_OR_CHECKSUM = "40";// khong

	public final static String SYSTEM_TYPE_LOCAL = "LOCAL";
	public final static String SYSTEM_TYPE_GLOBAL = "GLOBAL";

	public final static int SIM_IS_ONLINE = 2;
	public final static int SIM_IS_LOCKED = 4;
	public final static String DASH_CHAR = "_";
	public final static String ORDER_RV_ACCEPT = "A";
	public final static String ORDER_RV_PENDING = "P";
	public final static String ORDER_RV_REJECT = "R";

	// Trang thai don hang: C: hoan thanh; W: đang nạp; S: đã dừng lại
	public final static String ORDER_STATUS_COMPLETE = "C";
	public final static String ORDER_STATUS_WORKING = "W";
	public final static String ORDER_STATUS_STOP = "S";
	public final static String ORDER_STATUS_INNIT = "I";
	public final static String ORDER_STATUS_FAIL_AFTER_PROCESS = "F";

	public final static int ORDER_IS_PRIORITIZE = 1;
	public final static int ORDER_NON_PRIORITIZE = 0;
	// Bang luu các order va sdt pending
	public final static String HASH_ORDER_PENDING_TRANS = "KEY1_HASH_ORDERS_PENDING";// Column
																						// format:
																						// [orderId]_[Sdt]
	public final static int PROVIDER_IS_MATCH_CARD = 1;
	public final static int PROVIDER_NORMAL = 0;
	public final static int PROVIDER_PARTNER = 2;
	public final static String LIST_ERR_REQUEST = "LIST_REQUEST_RESPONSE_ERR";
	public final static String CHAR_NOT_AVAILABLE = "NA";
	public final static String CHAR_SPLIT = ";";
	public final static String KEY_ENQUEU_TRANS = "_ENQUE_TRANS";// QUEUE LƯU
																	// GIAO DỊCH
																	// ĐỂ AGENT
																	// QUÉT
	public final static String KEY_PREFIX_TRANS_FROM_PARTNER = "PTN_ASYN_";
	public final static String KEY_PREFIX_QUEUERESPONS = "TRANS_RS_";
	public final static String KEY_PREFIX_TELCO_CARD_SUCCES = "CARD_SC_OF_";

	public final static String CONNECTOR_STT_LOCKED = "K";
	public final static String CONNECTOR_STT_AVAIL = "A";

	public final static int CHARGE_TYPE_TOPUP = 1;
	public final static int CHARGE_TYPE_MATCH_CARD = 2;

	public final static String SET_SUCCESS_CARD = "SET_SUCCESS_CARD_";

	public final static int PR_DEPOSIT = 100;

	public final static int PR_BALANCE = 200;

	public final static int PR_PAYMENT = 300;

	public final static String QUEUENAME_CALLBACKLINK = "CALLBACKURL_QUEUE";

	// 1000 Gửi sms
	// 1001 Gửi OTP
	// 1002 Query số dư
	// 1003 Query trạng thái request

	public final static int SMS_PR_SEND = 1000;
	public final static int SMS_PR_SEND_OTP = 1001;
	public final static int SMS_PR_QUERYBALANCE = 1002;
	public final static int SMS_PR_QUERYREQUEST_STATUS = 1003;

	// 00 Nhận request thành công, chờ xử lý.
	// 01 Sai partnerId
	// 02 Không giải mã được dữ liệu gửi lên, kiểm tra lại key mã hóa
	// 03 Mã giao dịch đã tồn tại
	// 04 Client time không đúng định dạng
	// 05 Sai processing code
	// 06 Số điện thoại không đúng định dạng yêu cầu hoặc rỗng.
	// 07 Content rỗng.
	// 08 Không thấy requestId này(trả về trong trường ợp query trạng thái
	// request).
	// 99 Request pending.

	public final static String SMS_RQ_SUCCESS = "00";
	public final static String SMS_INVALID_PARTNERID = "01";
	public final static String SMS_DECRYPT_DATAFAIL = "02";
	public final static String SMS_REQUEST_ID_EXISTED = "03";
	public final static String SMS_INVALID_CLIENT_TIME = "04";
	public final static String SMS_INVALID_PR_CODE = "05";
	public final static String SMS_INVALID_TARGET_PHONE = "06";
	public final static String SMS_EMPTY_CONTENT = "07";
	public final static String SMS_REQUEST_ID_DONOT_EXIST = "08";
	public final static String SMS_REQUEST_BALANCE_IS_NOT_ENOUGH = "09";
	public final static String SMS_REQUEST_SENT_FAIL = "10";

	public final static String SMS_ALL_QUEUENAME = "TELCO_%1S_QUEUE";

	public static final int SMS_UNICODE_CONTENT_MAXLEN = 70;
	public static final int SMS_UNSIGN_CONTENT_MAXLEN = 160;

	public final static String SMS_ALL_SENT_SUCCESS_QUEUE = "ALL_SUCESS_QUEUE";
	public final static String SMS_ALL_ONLINE_SIM = "ALL_ONLINESIM_QUEUE";
}
