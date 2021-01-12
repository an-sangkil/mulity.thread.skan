package mulity.thread.custom.executer;

/**
 * <pre>
 * Description : 기본 Thread 테스트
 * @author skan
 * @since 2018.07.18
 * @version
 *
 * Copyright (C) 2018 by Mezzomedia.Inc. All right reserved.
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecuteExam {
    public static void main(String[] args) throws Exception {

        // 8개의 Thread 생성
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        //new ThreadPoolExecutor(8, 8,100L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        for (int i = 0; i < 50; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;

                    int poolSize = threadPoolExecutor.getPoolSize();
                    String threadName = Thread.currentThread().getName();
                    System.out.println("[총 스레드 개수: " + poolSize + "] 작업 스레드 이름: " + threadName);

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //int value = Integer.parseInt("숫자");
                }
            };

            //executorService.execute(runnable);
            executorService.submit(runnable);
            Thread.sleep(10);
        }

        System.out.println("============== END ");
        executorService.shutdown();

    }
}

