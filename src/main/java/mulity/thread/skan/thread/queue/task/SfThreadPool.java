package mulity.thread.skan.thread.queue.task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * <pre>
 * Class Name  : SfThreadPool.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2015. 1. 8.        ask               최초생성
 * </pre>
 *
 * @author ask
 * @since 2015. 1. 8.
 * @version 
 *
 * Copyright (C) 2014 by SKAN.COMPANY All right reserved.
 */
public class SfThreadPool extends ThreadPoolExecutor {

	
	public SfThreadPool(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}
	
	public SfThreadPool(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
		
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
	}
	
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
	}
	
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		super.afterExecute(r, t);
	}

}
