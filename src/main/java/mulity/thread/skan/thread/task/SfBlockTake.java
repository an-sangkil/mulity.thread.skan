
package mulity.thread.skan.thread.task;

import java.util.concurrent.BlockingQueue;

import mulity.thread.skan.model.User;
import mulity.thread.skan.thread.SfBlockRuner;
import mulity.thread.skan.utils.StateUtils;

/**
 * <pre>
 * Class Name  : SfExecuter.java
 * Description : 
 * Modification Information
 * 
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2014. 12. 17.        ask               최초생성
 * </pre>
 *
 * @author ask
 * @since 2014. 12. 17.
 * @version
 *
 *          Copyright (C) 2014 by SKAN.COMPANY All right reserved.
 */
public class SfBlockTake<T> implements Runnable {

	private BlockingQueue<T> queue;

	private static int count = 0;

	/**
	 * 
	 */
	public SfBlockTake(BlockingQueue<T> queue) {
		this.queue = queue;
	}

	public void run() {

		User user = new User();
		try {
			while (!queue.isEmpty()) {
				T t = queue.take();
				if (t instanceof User) {
					user = (User) t;
				}

				for (int i = 0; i < 1; i++) {
					Thread.sleep(5000);
					// System.out.println(Thread.currentThread().getName()+ "-[" + count+"]===== TEST : " + i + " [size = "+ queue.size()+"]");
				}
				System.out.println("Thread " + Thread.currentThread().getName()	+ " Start");
				System.out.println(user);
				count++;

				StateUtils.getInstance().removeItem(user.getKey());

			}

		} catch (Exception e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		} finally {
			//isShutdownNow();
		}

	}

	@Deprecated
	public boolean isShutdownNow() {
		boolean isShutdownNow = false;
		while (!isShutdownNow) {
			if (StateUtils.getInstance().getItemSize() == 0) {
				isShutdownNow = true;
				// int activeCount = threadPoolExecutor.getActiveCount();
				// int queueCount = threadPoolExecutor.getQueue().size();
				// int waitSeconds = (activeCount + queueCount) * 60 + 60;
				// System.out.println(waitSeconds);
				SfBlockRuner.getInstance().shutdown();
			}
		}
		return isShutdownNow;
	}

}
