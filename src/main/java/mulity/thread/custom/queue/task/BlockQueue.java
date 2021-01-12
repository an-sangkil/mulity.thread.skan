package mulity.thread.custom.queue.task;

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
public class BlockQueue<T> implements Runnable {
	
	private BlockingQueue<T> block;
	
	private T item;
	
	public BlockQueue() {
		
	}
	
	/**
	 * @param block
	 */
	public BlockQueue(BlockingQueue<T> block, T item) {
		super();
		this.block = block;
		this.item = item;
	}

	public void run() {
		
		System.out.println("put item : " + item +  "size : " + block.size());
		try {
			//block.add(item);
			block.put(item);
			//block.offer(item);
		} catch (IllegalStateException e) {
			if(StringUtils.contains(e.getMessage(), "Queue full")) {
				e.printStackTrace();
				System.out.println("SfBlockQueue = "  + e.getMessage());
			}
			Thread.currentThread().interrupt();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @return the block
	 */
	public BlockingQueue<T> getBlock() {
		return block;
	}
}
