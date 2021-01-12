package thread.pubsub;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2021 by CJENM|MezzoMedia. All right reserved.
 * @since 2021-01-12
 */
public class ExecutorServiceTest {

    public static void main(String[] args) throws Exception {

        final ParallelService service = new ParallelService();
        service.jobCreation("");

        // 여러개의 (5명의)  Consumer 가 온것처럼 가상의 호출 시행
        // 클라이언트가 여러개라는 가정으로 테스트 한것이나 Thread가 좀비가 되는 현상이 있다.
        // 차라리 컨슈머 쪽에서도 Take 할때 병렬처리가 가능하도록 해주는게 테스트 하기 좋다.
        /*for (int i = 0; i < 5; i++) {
            Thread consumers = new Thread(() -> {
                service.run();
            });
            consumers.setName("소비자" + (i + 1));
            consumers.start();
            ;
        }*/
        service.run();

        // 중지 요청( 이미 시작된 Task 는 실행하고, 새로운 작업은 받지 않는다.)
        ParallelService.consumer.shutdown();
        ParallelService.producer.shutdown();

        do {
            // 종료 요청이 있었는지 체크
            if (!ParallelService.consumer.isShutdown()) {
                //ParallelService.consumer.shutdown();
            }
            System.out.println(ParallelService.producer.awaitTermination(1,TimeUnit.MILLISECONDS));
            System.out.println(ParallelService.consumer.awaitTermination(1,TimeUnit.MILLISECONDS));
        }while (!ParallelService.consumer.awaitTermination(1,TimeUnit.SECONDS));


        //TimeUnit.SECONDS.sleep(10);


    }


    public static class ParallelService {

        protected static final BlockingQueue<String> queue = new ArrayBlockingQueue<>(100);//new ArrayBlockingQueue<>(100);
        protected static ExecutorService producer = Executors.newWorkStealingPool();
        protected static ExecutorService consumer = Executors.newWorkStealingPool();
        //protected static ThreadPoolExecutor producer = new ThreadPoolExecutor(4, 4, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));
        //protected static ThreadPoolExecutor consumer = new ThreadPoolExecutor(4, 4, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3));

        public void jobCreation(String data) {

            producer.execute(() -> {
                try {
                    for (int i = 0; ; i++) {
                        queue.put(String.valueOf(i));
                        // 큐 사이즈 출력
                        //System.out.printf("[%s] PUT %d : size = %d \n", Thread.currentThread().getName(), i, queue.size());

                        // PUT 대기시간
                        Thread.sleep(50);

                        if (i == 100) {
                            // i 값이 100 이 되면 종료
                            System.out.printf("[%s] PUT - END  %d : size = %d \n", Thread.currentThread().getName(), i, queue.size());
                            queue.put("end");
                            break;
                        }

                    }
                    //System.out.println("PUT ITEM : " + data + "size : " + queue.size() + ", THREAD = " + threadName);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }


        public void run() {

            consumer.execute(() -> {
                String job = "";
                do {
                    try {

                        job = queue.take();
                        Thread.sleep(100);
                        System.out.println("TAKE = " + job + ", THREAD = " + Thread.currentThread().getName());

                        if (job.equals("end")) {
                            System.out.println("end 조건에 충족되어 컨슈머를 종료 합니다.");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }

                } while (!job.equals("end"));
            });


        }
    }
}
