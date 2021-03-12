package thread.pubsub;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

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
public class ExecutorService2Test {

    public static void main(String[] args) throws Exception {

        // Producer 5개 생성
        final ParallelService service = new ParallelService();
        service.jobCreation("job_id_1");
        service.jobCreation("job_id_2");
        service.jobCreation("job_id_3");
        service.jobCreation("job_id_4");
        service.jobCreation("job_id_5");

        // 여러개의 (5명의)  Consumer 가 온것처럼 가상의 호출 시행
        // (1) 클라이언트가 여러개라는 가정으로 테스트 한것으로, Customer 에서 take() 함으로, 컨슈머를 여러개 생성할경우 Thread 가 무한대기 상태에 빠지지 않도록 한다.
        // (2) 컨슈머를 Producer 만큼 생성하거나,  take() 를 pool() 로 변경하여 사용한다.
        IntStream.rangeClosed(0, 7).forEach(value -> service.run());

        // 중지 요청( 이미 시작된 Task 는 실행하고, 새로운 작업은 받지 않는다.)
        ParallelService.consumer.shutdown();
        ParallelService.producer.shutdown();

        do {
            // 종료 요청이 있었는지 체크
            if (!ParallelService.consumer.isShutdown()) ParallelService.consumer.shutdown();
            if (!ParallelService.producer.isShutdown()) ParallelService.producer.shutdown();

            System.out.println(ParallelService.producer.awaitTermination(1, TimeUnit.MILLISECONDS));
            System.out.println(ParallelService.consumer.awaitTermination(1, TimeUnit.MILLISECONDS));

        } while (!ParallelService.consumer.awaitTermination(1, TimeUnit.SECONDS));

    }


    public static class ParallelService {

        protected static final BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);//new ArrayBlockingQueue<>(100);
        protected static final ExecutorService producer = Executors.newWorkStealingPool();
        protected static final ExecutorService consumer = Executors.newWorkStealingPool();
        //protected static ThreadPoolExecutor producer = new ThreadPoolExecutor(4, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        //protected static ThreadPoolExecutor consumer = new ThreadPoolExecutor(4, 4, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        public void jobCreation(String data) {

            producer.execute(() -> {

                String threadName = Thread.currentThread().getName();
                try {
                    queue.put(data);

                    // 큐 사이즈 출력
                    System.out.printf("PUT  , Thread - [%s] , DATA = [%s],  SIZE = [%d] \n", threadName, data, queue.size());

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }


        public void run() {

            consumer.execute(() -> {
                String job = "";
                
                // 해당 Collection 에 요소가 없는경우 true를 반환
                while (!queue.isEmpty()) {
                    try {
                        job = queue.take();
                        Thread.sleep(100);
                        System.out.printf("TAKE - [%s] , THREAD - [%s] \n", job, Thread.currentThread().getName());

                    } catch (Exception e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }

                }


            });


        }


        /**
         * 현재 실행중인 모든 작업을 종료하고,  실행 대기 중인 목록을 반환한하기
         */
        public void closeShutdownNow() {
            List<Runnable> unfinishedTasks = producer.shutdownNow();
            if (!unfinishedTasks.isEmpty()) {
                System.out.println("Not all tasks finished before calling close: " + unfinishedTasks.size());
            }
        }
    }
}
