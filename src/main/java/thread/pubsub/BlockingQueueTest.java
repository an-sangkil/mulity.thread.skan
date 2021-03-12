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
 * @version Copyright (C) 2014 by SKAN.COMPANY All right reserved.
 * @since 2014. 12. 17.
 */
public class BlockingQueueTest {

    /**
     * 생산자 클래스
     *
     * @author falbb
     */
    static class Producer implements Runnable {
        private final BlockingQueue<String> queue;

        public Producer(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        public void run() {

            // 날짜 포멧
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

            Date date = null;

            while (true) {
                try {

                    date = new Date();
                    // 큐에 넣기

                    for (int i = 0; ; i++) {

                        //queue.add(String.valueOf(i));
                        //queue.put(dateFormat.format(date));
                        queue.put(String.valueOf(i));

                        // 큐 사이즈 출력
                        System.out.printf("[%s] PUT %d : size = %d \n", Thread.currentThread().getName(), i, queue.size());

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
     *
     * @author falbb
     */
    static class Consumer implements Runnable {

        private BlockingQueue<String> queue;

        public Consumer(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        public void run() {

            while (true) {
                try {

                    System.out.printf("[%s] TAKE : %s \n", Thread.currentThread().getName(), queue.take());

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

        // 여러개의 (3명의) Provider 가 상품을 생산하는것 처럼 호출
        //ThreadPoolExecutor producer = new ThreadPoolExecutor(4, 4, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3));
        for (int i = 0; i < 3; i++) {
            Thread producer = new Thread(new Producer(queue));
            producer.setName("생산자_" + i);
            producer.start();

            // ThreadPool 사용시 주석 해제
            //producer.execute(new Producer(queue));
        }

        // 여러개의 (3명의)  Consumer 가 온것처럼 가상의 호출 시행
        // (1) 클라이언트가 여러개라는 가정으로 테스트 한것으로, Customer 에서 take() 함으로,
        //     컨슈머를 여러개 생성할경우 Thread 가 무한대기 상태에 빠지지 않도록 한다.
        // (2) 컨슈머를 Producer 만큼 생성하거나,  take() 를 pool() 로 변경하여 사용한다.

        // ThreadPoolExecutor consumer = new ThreadPoolExecutor(4, 4, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3));
        for (int i = 0; i < 3; i++) {
            Thread consumers = new Thread(new Consumer(queue));
            consumers.setName("소비자" + (i + 1));
            consumers.start();

            // ThreadPool 사용시 주석 해제
            //consumer.execute(new Consumer(queue));

        }

    }

}