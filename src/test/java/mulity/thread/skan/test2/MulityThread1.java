package mulity.thread.skan.test2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
public class MulityThread1 {

	public static void main(String[] args) {
		//ThreadPoolExecutor threadPool = new ThreadPoolExecutor(4, 4, 60,TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(4, 4, 60,TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3));
		
		for (int i = 0; i < 7; i++) {

			threadPool.execute(new Runnable() {
				
				public void run() {
					
					System.out.println( "Thread " + Thread.currentThread().getName() + " Start" );
					
					try {
						Thread.sleep(2000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		threadPool.shutdown();

	}
}
