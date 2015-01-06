package mulity.thread.skan.test1;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
 
public class TestBlockingQ {
 
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
						queue.put(String.valueOf(i));
						//queue.put(dateFormat.format(date));
						
						// 큐 사이즈 출력
						System.out.printf("[%s] : size = %d \n", Thread.currentThread().getName(), queue.size());
						
						// 대기시간
						Thread.sleep(50);					
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
 
					System.out.printf("[%s] : %s \n",Thread.currentThread().getName(), queue.take());
 
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
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);
 
		//Thread producer = new Thread(new Producer(queue));
		//producer.setName("생산자1");
		//producer.start();
		
		ThreadPoolExecutor producer = new ThreadPoolExecutor(4, 4, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3));
		producer.execute(new Producer(queue));
 
		for (int i = 0; i < 5; i++) {
			//Thread consum = new Thread(new Consumer(queue));
			//consum.setName("소비자"+(i+1));
			//consum.start();
			ThreadPoolExecutor consum = new ThreadPoolExecutor(4, 4, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3));
			consum.execute(new Consumer(queue));
		}
 
	}
 
}