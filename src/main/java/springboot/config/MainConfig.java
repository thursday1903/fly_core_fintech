package springboot.config;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class MainConfig extends BaseConfig {
	private final static String SYSTEM_CONFFIG = "/config/main.cfg";

	public static int REDIS_MAX_CONN = 50;
	public static int REDIS_MIN_IDLE = 5;
	public static int REDIS_MAX_IDLE = 50;
	public static String REDIS_HOST = "192.168.1.1";
	public static String REDIS_PASSWORD = "192.168.1.1";
	public static Boolean REDIS_ENABLE_AUTHEN = true;
	public static int REDIS_PORT = 6379;
	public static int REDIS_DATAFILE = 2;

	public static String mailServer = "smtp.gmail.com";
	public static int mailServerPort = 465;
	public static String mailFrom = "vietda@imediatech.com.vn";
	public static String mailFromPass = "mvbexbumfnrixbmw";
	public static Boolean mailEnableSSl = true;
	public static String listAdminEmail = "vietda@imediatech.com.vn,";
	public static String PARTNER_LIMIT_BUDGET = "FUNMOBI:100000000";
	public static Boolean QUEUE_LOGS_FLAG = true;;
	public static String MONGO_HOST = "localhost";
	public static String MONGO_DATABASE = "fintech_data";
	public static Integer MONGO_PORT = 27017;
	
	public static String ERROR_MESSAGE_MAPPING = "200	SUCCESS" + "&99	TRANSACTION PENDING" + "&11	TRANSACTION FAIL"
			+ "&101	TRANSACTION FAIL-SYSTEM-UNSTABLE" + "&102	TRANSACTION DUPLICATE REQUESTID"
			+ "&103	INVALID SIGNATURE" + "&110	INVALID PARTNERCODE" + "&111	PARTNERCODE DELETED"
			+ "&113	REQUIRE OPERATION PARAM" + "&114	INVALID OPERATION" + "&115	INVALID BANK NO"
			+ "&117	WRONG ACC NO" + "&118	WRONG ACC NAME" + "&119	REQUIRE REFERENCEID"
			+ "&120	DUPLICATE REFERENCEID" + "&121	NO REFERENCE ID FOUND" + "&122	REQUIRE REQUEST AMOUNT"
			+ "&123	INVALID REQUEST AMOUNT" + "&124	INVALID MEMO" + "&129	BUDGET NOT ENOUGH OR BUDGET EXPIRE"
			+ "&130	INVALID PARAMS" + "&131	CLIENT TIME WRONG WITH SERVER" + "&132	NO FEE ASSIGN TO PARTNER";

	public static String TELEGRAM_URL = "https://api.telegram.org/bot973875366:AAGaP3YMEQepGDpqT0IlSwRvE6LS_Iga9x0/sendMessage";;
	public static String TELEGRAM_CHAT_ID = "-244112350";
	public static int TIME_SPAN_TO_LOAD_SUCCESS_DATA_TO_CACHE = 90;

	public MainConfig(String configPath) {
		super(configPath);
		// TODO Auto-generated constructor stub
		getAllParas();
		instance = this;
	}

	@Override
	protected void getAllParas() {
		// TODO Auto-generated method stub
		super.getAllParas();

		REDIS_MAX_CONN = getInt("REDIS_MAX_CONN", REDIS_MAX_CONN);
		REDIS_MIN_IDLE = getInt("REDIS_MIN_IDLE", REDIS_MIN_IDLE);
		REDIS_MAX_IDLE = getInt("REDIS_MAX_IDLE", REDIS_MAX_IDLE);
		REDIS_HOST = properties.getProperty("REDIS_HOST", REDIS_HOST);
		REDIS_PORT = getInt("REDIS_PORT", REDIS_PORT);
		REDIS_DATAFILE = getInt("REDIS_DATAFILE", REDIS_DATAFILE);

		mailServer = properties.getProperty("mailServer", mailServer).trim();
		mailServerPort = getInt("mailServerPort", mailServerPort);
		mailFrom = properties.getProperty("mailFrom", mailFrom).trim();
		mailFromPass = properties.getProperty("mailFromPass", mailFromPass).trim();
		mailEnableSSl = getBoolProperty("mailEnableSSl", mailEnableSSl);
		listAdminEmail = properties.getProperty("listAdminEmail", listAdminEmail).trim();
		PARTNER_LIMIT_BUDGET = properties.getProperty("PARTNER_LIMIT_BUDGET", PARTNER_LIMIT_BUDGET).trim();
		TELEGRAM_URL = properties.getProperty("TELEGRAM_URL", TELEGRAM_URL).trim();
		TELEGRAM_CHAT_ID = properties.getProperty("TELEGRAM_CHAT_ID", TELEGRAM_CHAT_ID).trim();
		TIME_SPAN_TO_LOAD_SUCCESS_DATA_TO_CACHE = getInt("TIME_SPAN_TO_LOAD_SUCCESS_DATA_TO_CACHE",
				TIME_SPAN_TO_LOAD_SUCCESS_DATA_TO_CACHE);
		REDIS_PASSWORD = properties.getProperty("REDIS_PASSWORD", REDIS_PASSWORD).trim();
		REDIS_ENABLE_AUTHEN = getBoolProperty("REDIS_ENABLE_AUTHEN", REDIS_ENABLE_AUTHEN);
		QUEUE_LOGS_FLAG = getBoolProperty("QUEUE_LOGS_FLAG", QUEUE_LOGS_FLAG);
		MONGO_HOST = properties.getProperty("MONGO_HOST", MONGO_HOST);
		MONGO_DATABASE = properties.getProperty("MONGO_DATABASE", MONGO_DATABASE);
		MONGO_PORT = getInt("MONGO_PORT", MONGO_PORT);
	}

	private static MainConfig instance = null;

	public static MainConfig GetInstance() {
		if (instance == null)
			new MainConfig(SYSTEM_CONFFIG);
		return instance;
	}

	public void reloadConfig() {
		loadProperties();
	}

	static {
		MainConfig.GetInstance();
	}

	public static void main(String[] args) {

		try {
			String data = "Bên B cam kết và đảm bảo mục đích thực hiện các giao dịch tại Hợp đồng này là hợp lệ và hợp pháp, không vi phạm quy định của pháp luật, không trái đạo đức xã hội. Đặc biệt, Bên B cam kết không cố ý hoặc vô ý thực hiện hoạt động rửa tiền qua hệ thống của Bên A, đảm bảo xây dựng và kiểm soát hệ thông kết nối từ Bên B đến Bên A để không cho phép bất cứ hoạt động rửa tiền qua hệ thống của Bên A";

			String nfdNormalizedString = Normalizer.normalize(data, Normalizer.Form.NFD);
			Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

			String output = pattern.matcher(nfdNormalizedString).replaceAll("").toLowerCase().replace("đ", "d")
					.toUpperCase();
			System.out.println(output);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
