package vn.com.group.threadfactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Lop threadpool xu ly execute cac thread ben trong he thong
 *
 * @author VietTung
 */
public class HttpThreadPool {

	static ThreadPoolExecutor executor = null;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HttpThreadPool() {
		// TODO Auto-generated constructor stub
		// executor = new ThreadPoolExecutor(Config.corePoolSize,
		// Config.maximumPoolSize, Config.keepAliveTime, TimeUnit.SECONDS,
		// new LinkedBlockingDeque<Runnable>(Config.maximumPoolSize),
		// new ThreadPoolExecutor.DiscardPolicy());
		LinkedBlockingQueue queue = new LinkedBlockingQueue<>(20);
		executor = new ThreadPoolExecutor(200, 200, 60, TimeUnit.SECONDS, queue, new RejectedExecutionHandlerImpl());
		executor.allowCoreThreadTimeOut(true);
		// executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
		// @Override
		// public void rejectedExecution(Runnable r,
		// ThreadPoolExecutor executor_) {
		// /*
		// * This does the actual put into the queue. Once the max
		// * threads have been reached, the tasks will then queue up.
		// */
		// // executor_.geteue().put(r);
		// System.out.println("Reject Thread");
		// }
		// });

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>THREAD POOL SIZE:" + executor.getCorePoolSize());
		instance = this;
	}

	private static HttpThreadPool instance = null;

	public static HttpThreadPool GetInstance() {
		if (instance == null)
			new HttpThreadPool();
		return instance;
	}

	public ThreadPoolExecutor getThreadExecutor() {
		return executor;
	}

	public void execute(Runnable runable) {
		executor.execute(runable);
	}

	public void stop() {
		executor.shutdown();
		instance = null;

	}

	public void cleanPool() {
		executor.shutdown();
	}

	public void getThreadPoolInfo() {
		System.out.println("Total complete Thread=" + executor.getCompletedTaskCount());
		System.out.println("Current Pool size=" + executor.getPoolSize());
		System.out.println("Get task count=" + executor.getTaskCount());
		int activeCount = executor.getActiveCount();
		System.out.println("Get active count=" + executor.getActiveCount());
		int queueSize = executor.getQueue().size();
		System.out.println("Get queue size=" + executor.getQueue().size());

		int notcomplete = queueSize - activeCount;
		while (notcomplete > 0) {
			System.out.println("Not complete=" + notcomplete);
			activeCount = executor.getActiveCount();
			queueSize = executor.getQueue().size();
			notcomplete = queueSize - activeCount;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getThreadPoolInfo2() {
		String info = "";
		ThreadPoolInfo poolInfo = new ThreadPoolInfo();
		poolInfo.setPoolName("HttpThreadPool");
		// System.out.println("Total complete Thread="
		// + executor.getCompletedTaskCount());
		poolInfo.setComplateTask(executor.getCompletedTaskCount());

		// System.out.println("Current Pool size=" + executor.getPoolSize());
		poolInfo.setPoolSize(executor.getPoolSize());
		// System.out.println("Get task count=" + executor.getTaskCount());
		poolInfo.setTaskCount(executor.getTaskCount());
		int activeCount = executor.getActiveCount();
		poolInfo.setActiveCount(activeCount);
		// System.out.println("Get active count=" + executor.getActiveCount());
		int queueSize = executor.getQueue().size();
		poolInfo.setQueueSize(queueSize);
		// System.out.println("Get queue size=" + executor.getQueue().size());

		Long notcomplete = queueSize + poolInfo.getPoolSize() - activeCount;
		poolInfo.setNot_complete_task(notcomplete);
		info = poolInfo.toString();
		return info;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {

			System.out.println("oh yes" + i);
			// NormalLog.Getlogger().trace("oh yes" + i);
			// NormalLog.Getlogger().info("oh yes" + i);
			HttpThreadPool.GetInstance().execute(new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					process();
				}

				void process() {
					System.out.println("fffffffffffffffff>>>>>>>>>>>>>" + Thread.currentThread().getId());
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}));
		}
		System.out.println(HttpThreadPool.GetInstance().getThreadPoolInfo2());
		while (HttpThreadPool.GetInstance().getThreadExecutor().getActiveCount() > 0) {
		}
		// System.out.println(HttpThreadPool.GetInstance().getThreadPoolInfo2());
		// System.out.println(HttpThreadPool.GetInstance().getThreadPoolInfo2());

		HttpThreadPool.GetInstance().stop();
	}

}
