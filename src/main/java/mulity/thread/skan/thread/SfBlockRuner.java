package mulity.thread.skan.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import mulity.thread.skan.thread.handler.SfRejectedExecutionHandler;
import mulity.thread.skan.thread.task.SfBlockQueue;
import mulity.thread.skan.thread.task.SfBlockTake;
import mulity.thread.skan.thread.task.SfThreadPool;
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
 * Copyright (C) 2014 by SKAN.COMPANY All right reserved.
 */
public class SfBlockRuner<T> {
	
	
	private static volatile SfBlockRuner<?> INSTANCE;
	protected static volatile boolean isShutdownNow = false;
	
	private SfBlockRuner() {
		createThreadPoolExecute(2, 2 ,60);
	}
	
	private static volatile SfThreadPool threadPoolExecutor;

	@SuppressWarnings("rawtypes")
	public static SfBlockRuner<?> getInstance () {
		
		if( INSTANCE == null) {
			synchronized (SfBlockRuner.class) {
				INSTANCE = new SfBlockRuner();
			}
		}
		
		return INSTANCE;
	}
	
	
	public void createThreadPoolExecute(int corePoolSize, int maximumPoolSize , int keepAliveTime) {
		if( threadPoolExecutor  == null || threadPoolExecutor.isTerminated()) {
			threadPoolExecutor = new SfThreadPool(corePoolSize, maximumPoolSize , keepAliveTime , TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		} 
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
			//threadPoolExecutor.execute(new SfBlockQueue<T>(queue, t));
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
	public void runner (BlockingQueue<T>  blockingQuene) throws InterruptedException {
		
		//ThreadPoolExecutor threadPoolExecutor= new ThreadPoolExecutor(2, 2, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new SfRejectedExecutionHandler());
		
		try {
			
			//System.out.println(threadPoolExecutor.isTerminated());
			//System.out.println(threadPoolExecutor.isTerminating());
			//System.out.println(threadPoolExecutor. getActiveCount());
			
			//System.out.println("================ RUNNER ===================");
			//System.out.println("status isTerminated= " + threadPoolExecutor.isTerminated());
			//System.out.println("Queue Size = " + blockingQuene.size());
			
			//sfblockTakeExecuter.execute(new SfBlockTake<T>(blockingQuene));
			//int activeCount = threadPoolExecutor.getActiveCount();
			//int queueCount = threadPoolExecutor.getQueue().size();
			
			//System.out.printf("시간 = %d Q= %d" , activeCount, queueCount);
			//System.out.println("");
			
			//threadPoolExecutor.awaitTermination(10, TimeUnit.SECONDS);
			//if(blockingQuene.size() == 1) {
			//System.out.println("blockingQueneOld=" + blockingQuene.size());
				threadPoolExecutor.execute(new SfBlockTake<T>(blockingQuene));
			//}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			//System.out.println("CompletedTask Count =  " + threadPoolExecutor.getCompletedTaskCount());
			//System.out.println("blockingQueneNew=" + blockingQuene.size());			
			
			//System.out.println("StateUtils.getInstance().getItemSize() = " + StateUtils.getInstance().getItemSize());
			//isShutdownNow();
			//boolean await = threadPoolExecutor.awaitTermination(20, TimeUnit.SECONDS);
			//boolean await = threadPoolExecutor.allowsCoreThreadTimeOut();
			
			//System.out.println("await = " + await);
			//sfblockTakeExecuter.shutdown();
			//threadPoolExecutor.shutdown();
		}
		
	}
	
	public void shutdown() {
		
		threadPoolExecutor.shutdown();
		
		if ( !threadPoolExecutor.isTerminated()) {
			
			try {
				boolean await = threadPoolExecutor.awaitTermination(5, TimeUnit.NANOSECONDS);
				System.out.println(await);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
