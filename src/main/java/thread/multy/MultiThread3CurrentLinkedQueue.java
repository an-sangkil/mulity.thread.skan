package thread.multy;

import mulity.thread.model.User;

import java.util.concurrent.ConcurrentLinkedQueue;
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
public class MultiThread3CurrentLinkedQueue {

//	static  ThreadPoolExecutor threadPool = new ThreadPoolExecutor(4, 4, 60, TimeUnit.SECONDS, new ConcurrentLinkedQueue<Runnable>());
//
//	public static void main(String[] args) {
//
//
//
//		for (int i = 0; i < 10; i++) {
//			threadPool.execute(new User("name", String.valueOf(i)));
//		}
//
//		threadPool.shutdown();
//
//	}
}
