package mulity.thread.skan.thread.task;

import java.util.concurrent.BlockingQueue;

import org.apache.commons.lang.StringUtils;

/**
 * <pre>
 * Class Name  : SfExecuteBlock.java
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
public class SfBlockQueue<T> implements Runnable {
	
	private BlockingQueue<T> block;
	
	private T item;
	
	/**
	 * @param block
	 */
	public SfBlockQueue(BlockingQueue<T> block, T item) {
		super();
		this.block = block;
		this.item = item;
	}

	public void run() {
		
		System.out.println("put item : " + item +  "size : " + block.size());
		//block.put(item);
		try {
			block.add(item);
			//block.offer(item);
		} catch (IllegalStateException e) {
			if(StringUtils.contains(e.getMessage(), "Queue full")) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
