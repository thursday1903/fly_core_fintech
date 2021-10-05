package springboot.bussiness;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import springboot.utils.DateConvert;

public class BussinesStringGenerator {

	private static BussinesStringGenerator instance;

	private static AtomicInteger atomicInteger = new AtomicInteger(0);
	private Random random = new Random(System.currentTimeMillis());

	public static BussinesStringGenerator getInstance() {
		if (instance == null) {
			new BussinesStringGenerator();
		}
		return instance;
	}

	public BussinesStringGenerator() {
		super();
		// TODO Auto-generated constructor stub
		instance = this;
	}

	public synchronized String generateUniqueTransId() {
		String current = DateConvert.dateToString(new Date(), "yyyyMMddHHmmssSSS");
		int ranNumber = random.nextInt(9999);
		String nextIntNumber = String.format("%04d", atomicInteger.getAndIncrement());
		if (atomicInteger.get() == 9999) {
			synchronized (atomicInteger) {
				System.out.println("RESET COUNTER");
				atomicInteger.set(0);
			}
		}
		return current + ranNumber + nextIntNumber;

	}

	public static void main(String[] args) {
		for (int i = 0; i < 200; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("NEW TRANSID:" + BussinesStringGenerator.getInstance().generateUniqueTransId());
				}
			}).start();

		}

	}
}
