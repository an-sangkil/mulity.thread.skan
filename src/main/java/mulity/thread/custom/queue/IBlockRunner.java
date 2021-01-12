package mulity.thread.custom.queue;

import java.util.concurrent.BlockingQueue;

/**
 * <pre>
 * Class Name  : ISfBlockRunner.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2014. 12. 24.        ask               최초생성
 * </pre>
 *
 * @author ask
 * @since 2014. 12. 24.
 * @version 
 *
 * Copyright (C) 2014 by SKAN.COMPANY All right reserved.
 */
public interface IBlockRunner<T> {
	
	public void setMuiltyThreadSetItem(T t, BlockingQueue<T> queue , int corePoolSize, int maximumPoolSize , int keepAliveTime);
}
