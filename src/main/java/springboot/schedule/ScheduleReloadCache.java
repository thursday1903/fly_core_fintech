package springboot.schedule;

import java.math.BigDecimal;
import java.math.MathContext;

import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import springboot.bussiness.BussinessData;
import springboot.bussiness.BussinessLoanProcess;

@Component
public class ScheduleReloadCache {
	// private static final Logger log = LoggerFactory.getLogger(Perses.class);

	public static void main(String[] args) {
//		SpringApplication.run(ScheduleReloadCache.class, args);
		BigDecimal bigdcm = new BigDecimal(0);
		MathContext mc = new MathContext(10);
		for (int i = 0; i < 10; i++) {

			bigdcm.add(new BigDecimal(i), mc);
		}
		System.out.println(bigdcm.longValue());
	}

	@Scheduled(cron = "${app.cron.scan_reload_mongo_data}", zone = "Asia/Bangkok")
	private void loadStableData() {
		// TODO Auto-generated method stub
		BussinessData.getInstance().loadBorrower();
		BussinessData.getInstance().loadProducts();
		BussinessData.getInstance().loadExtraFee();
		BussinessData.getInstance().loadQuestions();
		BussinessData.getInstance().loadRateConfig();
		BussinessData.getInstance().loadSponsor();
		BussinessData.getInstance().loadSystemConfigure();
		BussinessData.getInstance().loadTblBank();
		BussinessData.getInstance().loadTblBrand();
		BussinessData.getInstance().loadInsuranceProvider();
		BussinessData.getInstance().loadInsuranceProviderBanks();
		BussinessData.getInstance().loadTblBlackList();
		BussinessData.getInstance().loanInsuranceFee();
		BussinessData.getInstance().loanInsuranceFeeMapping();
		BussinessData.getInstance().loanInsuranceFeeMapApi();
		BussinessData.getInstance().loanInsuranceFeeApi();
		BussinessData.getInstance().loadInsuranceProviderApi();
		BussinessData.getInstance().loadBranch();
		BussinessData.getInstance().loadTransactionRoom();
		BussinessData.getInstance().loadVaAcc();
	}

	@Async
	@Scheduled(cron = "${app.cron.scan_notify_email}", zone = "Asia/Bangkok")
	private void scanNotifyEmail() {
		// TODO Auto-generated method stub
		BussinessLoanProcess.getInstance().composeNotifyEmailAndPushQueue();
	}

}
