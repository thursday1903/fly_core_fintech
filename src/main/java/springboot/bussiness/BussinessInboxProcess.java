package springboot.bussiness;

import springboot.logs.Logs;

public class BussinessInboxProcess {
	/**
	 * static Singleton instance.
	 */
	private static volatile BussinessInboxProcess instance;

	final static Logs LOGGER = new Logs(BussinessInboxProcess.class);

	/**
	 * Private constructor for singleton.
	 */
	private BussinessInboxProcess() {
	}

	/**
	 * Return a singleton instance of BussinessLoanProcess.
	 */
	public static BussinessInboxProcess getInstance() {
		// Double lock for thread safety.
		if (instance == null) {
			synchronized (BussinessInboxProcess.class) {
				if (instance == null) {
					instance = new BussinessInboxProcess();
				}
			}
		}
		return instance;
	}
	
	public void processCreateInbox()
	{
		
	}
}
