
package mulity.thread.custom.queue.task;

import java.util.concurrent.BlockingQueue;

import mulity.thread.model.User;
import mulity.thread.utils.StateUtils;

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
public class BlockTake<T> implements Runnable {

	private BlockingQueue<T> queue;

	private static int count = 0;

	/**
	 * 
	 */
	public BlockTake(BlockingQueue<T> queue) {
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
		}

	}

}
