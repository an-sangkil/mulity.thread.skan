package thread.multy;

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
public class MultiThread1ArrayBlockingQueue {

	public static void main(String[] args) {
		
		// 단순한 ArrayBlockingQueue 사용법
		// (1)정확한 순서를 보장하지 않는다.
		// (2)임계영역을 지정해 주어야 한다(capacity), fair 모드를 설정하여 Multi Thread 를 제한할수 있다.
		//     - 임계영역을 벗어나면 오류 발생됨 ,capacity 와 test data 를 조정해가며 테스트 해볼수 있다.

		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(4, 4, 60,TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));
		
		for (int i = 0; i < 20; i++) {
			threadPool.execute(new Runnable() {
				public void run() {
					System.out.println( "Thread " + Thread.currentThread().getName() + " Start" );
					try {
						Thread.sleep(2000);
					} catch (Exception e) {
						e.printStackTrace();
						
					} finally {
						Thread.currentThread().interrupt();
					}
				}
			});
		}
		
		//threadPool.shutdown();

	}
}
