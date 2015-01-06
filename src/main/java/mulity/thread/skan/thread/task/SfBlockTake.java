package mulity.thread.skan.thread.task;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;

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
 * Copyright (C) 2014 by SKAN.COMPANY All right reserved.
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

		try {
			Thread.sleep(1000);
			while (!queue.isEmpty()) {
				T t = queue.take();
				//System.out.println("take item : " + queue.take() );
				for (int i = 0; i < 2; i++) {
					Thread.sleep(3000);
					System.out.println("a[" + count+"]===== TEST : " + i  + " [size = "+ queue.size()+"]");
				}
				System.out.println("take item : " + t.toString());
				count++ ;
				// TODO :: 엔진 호출 구현~~
				
				
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		
	}

	
}
