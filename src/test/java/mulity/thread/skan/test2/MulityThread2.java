package mulity.thread.skan.test2;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import mulity.thread.skan.model.User;

/**
 * <pre>
 * Class Name  : MulityThread1.java
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
public class MulityThread2 {

	public static void main(String[] args) {
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(4, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

		for (int i = 0; i < 10; i++) {

			threadPool.execute(new User("name", String.valueOf(i)));
		}
		
		threadPool.shutdown();

	}
}
