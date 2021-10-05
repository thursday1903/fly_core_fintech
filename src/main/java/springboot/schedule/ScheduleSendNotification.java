package springboot.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import springboot.bussiness.BussinessData;

@Component
public class ScheduleSendNotification {
	// private static final Logger log = LoggerFactory.getLogger(Perses.class);

	public static void main(String[] args) {
		SpringApplication.run(ScheduleSendNotification.class, args);
	}

//	@Scheduled(cron = "${app.cron.scan_reload_mongo_data}", zone = "Asia/Bangkok")
	private void loadStableData() {
		// TODO Auto-generated method stub
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
	}

}
