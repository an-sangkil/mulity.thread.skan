package thread.pubsub;

import java.util.concurrent.*;

/**
 * 간단한 구독 발행 예제 코드
 * 	(1) 26개의 공간인 Queue를 생성한다.
 * 	(2) 케릭터셋 A~Z 까지 발행한다.
 * 	(3) 2개의 Executor 로 1초에 한번씩 데이터를 Take 해온뒤 처리한다.
 * 	(4)케릭터셋 F를 만나면 Thread 를 종료한다.
 * 	(5) F를 만나면 종료됨으로 Z까지 넣었을대 Thread 는 interrupt exception 을 생성하고 종료된다.
 *
 * 	테스트
 * 	 (1) Blocking Queue 의 사이즈를 줄여가며 어떻게 동작하는지 확인한다.
 * 	 (2) Queue 의 add(), put() 의 특징을 파악한다.
 * 	     - add : queue 가 가득 차 있으면 오류룰 낸다.
 * 	     - put : 공간이 남아 다시 작업을 할당 가능 할때 까지 대기한다.
 *
 *
 *
 */
public class ExecutorExample2 {
	public static void main(String[] args) throws InterruptedException {
		final BlockingQueue<Character> bq = new ArrayBlockingQueue<Character>(26);
		final ExecutorService executor = Executors.newFixedThreadPool(3);
		
		//producer
		Runnable producer;
		producer = new Runnable() {
			public void run() {
				for (char ch = 'A'; ch <= 'Z'; ch++) {
					try {
						bq.put(ch);
						System.out.println("putting:" + ch + " thread name : " + Thread.currentThread().getName());
					} catch (InterruptedException ie) {
						Thread.currentThread().interrupt();
					}
				}
			}
		};
		executor.execute(producer);

		//consumer
		Runnable consumer;
		consumer = new Runnable() {
			public void run() {
				char ch = '\0';
				do {
					try {
						ch = bq.take();
						System.out.println("getting:" + ch  + " thread name : " + Thread.currentThread().getName());
					} catch (InterruptedException ie) {
						Thread.currentThread().interrupt();
						ie.printStackTrace();
					}
				} while (ch != 'F');
				executor.shutdownNow();
			}
		};
		executor.execute(consumer);
	}
}