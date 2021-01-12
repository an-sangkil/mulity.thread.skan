package thread.pubsub;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * Class Name  : asd.java
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
public class BlockingQueueTest {
 
	/**
	 * 생산자 클래스
	 * @author falbb
	 *
	 */
	static class Producer implements Runnable {
		private BlockingQueue<String> queue;
 
		public Producer(BlockingQueue<String> queue) {
			this.queue = queue;
		}
 
		public void run() {
 
			// 날짜 포멧
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
 
			Date date = null;
 
			while(true) {
				try {
 
					date = new Date();
					// 큐에 넣기
					
					for (int i=0;; i++) {
						
						//queue.add(String.valueOf(i));
						//queue.put(dateFormat.format(date));
						queue.put(String.valueOf(i));

						// 큐 사이즈 출력
						System.out.printf("[%s] PUT %d : size = %d \n", Thread.currentThread().getName(), i,  queue.size());
						
						// PUT 대기시간
						Thread.sleep(100);
					}
 
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
 
	}
 
	/**
	 * 소비자 클래스
	 * @author falbb
	 *
	 */
	static class Consumer implements Runnable {
 
		private BlockingQueue<String> queue;
		public Consumer(BlockingQueue<String> queue) {
			this.queue = queue;
		}
 
		public void run() {
 
			while(true) {
				try {
 
					System.out.printf("[%s] TAKE : %s \n",Thread.currentThread().getName(), queue.take());
 
					// 대기 시간
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
 
	}
 
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
 
		// BlockingQueue사용 방법
		// ex1)BlockingQueue<String> queue = new ArrayBlockingQueue<String>(큐의 맥스 사이즈);
		// ex2)BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(100);
 
		//Thread producer = new Thread(new Producer(queue));
		//producer.setName("생산자1");
		//producer.start();
		
		ThreadPoolExecutor producer = new ThreadPoolExecutor(4, 4, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3));
		producer.execute(new Producer(queue));

		ThreadPoolExecutor consumer = new ThreadPoolExecutor(4, 4, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3));
		for (int i = 0; i < 5; i++) {
			Thread consumers = new Thread(new Consumer(queue));
			consumers.setName("소비자"+(i+1));
			consumers.start();

			//consumer.execute(new Consumer(queue));

		}

	}
 
}