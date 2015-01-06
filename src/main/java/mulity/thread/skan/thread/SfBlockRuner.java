package mulity.thread.skan.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import mulity.thread.skan.thread.handler.SfRejectedExecutionHandler;
import mulity.thread.skan.thread.task.SfBlockQueue;
import mulity.thread.skan.thread.task.SfBlockTake;

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
public class SfBlockRuner<T> {
	
	
	private static volatile SfBlockRuner<?> INSTANCE;
	
	public SfBlockRuner() {}

	@SuppressWarnings("rawtypes")
	public static SfBlockRuner<?> getInstance () {
		
		if( INSTANCE == null) {
			synchronized (SfBlockRuner.class) {
				INSTANCE = new SfBlockRuner();
			}
		}
		
		return INSTANCE;
	}
	
	
	public void setItem (T t, BlockingQueue<T> queue , int corePoolSize, int maximumPoolSize , int keepAliveTime) {
		
		ThreadPoolExecutor sfblockTakeExecuter = new ThreadPoolExecutor(corePoolSize, maximumPoolSize , keepAliveTime , TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),new SfRejectedExecutionHandler());
		
		try {
			sfblockTakeExecuter.execute(new SfBlockQueue<T>(queue, t));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sfblockTakeExecuter.shutdown();
		}
	}
	
	/**
	 * default Item set cpu 1 / 1
	 *                  time type : 60 seconds 
	 *                   
	 * @param t
	 * @param queue
	 */
	public void setItem (T t, BlockingQueue<T> queue ) {
		
		ThreadPoolExecutor sfblockTakeExecuter= new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		
		try {
			sfblockTakeExecuter.execute(new SfBlockQueue<T>(queue, t));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sfblockTakeExecuter.shutdown();
		}
	}
	
	public void singleSetItem(T t, BlockingQueue<T> queue){
		
		Thread sofosItem = new Thread(new SfBlockQueue<T>(queue, t));
		
		try {
			sofosItem.setName("SOFOS-T1");
			sofosItem.start();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sofosItem.interrupt();
		}
	}
	
	/**
	 * default Item set cpu 2 / 2
	 *                  time type : 60 seconds 
	 *                   
	 * @param blockingQuene
	 */
	public void runner (BlockingQueue<T>  blockingQuene) {
		
		ThreadPoolExecutor sfblockTakeExecuter= new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new SfRejectedExecutionHandler());
		
		try {
			sfblockTakeExecuter.execute(new SfBlockTake<T>(blockingQuene));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sfblockTakeExecuter.shutdown();
		}
		
	}

}
