package springboot.bussiness;

import springboot.logs.Logs;

public class BussinessAccount {
/**
 * static Singleton instance.
 */
private static volatile BussinessAccount instance;
final static Logs LOGGER = new Logs(BussinessAccount.class);

/**
 * Private constructor for singleton.
 */
private BussinessAccount() {
}

/**
 * Return a singleton instance of BussinessAccount.
 */
public static BussinessAccount getInstance() {
	// Double lock for thread safety.
	if (instance == null) {
		synchronized (BussinessAccount.class) {
			if (instance == null) {
				instance = new BussinessAccount();
			}
		}
	}
	return instance;
}
}
