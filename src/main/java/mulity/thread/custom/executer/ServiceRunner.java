package mulity.thread.custom.executer;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * Description :
 * @author mezzomedia
 * @since 2018.07.18
 * @version
 *
 * Copyright (C) 2018 by Mezzomedia.Inc. All right reserved.
 */
public class ServiceRunner {


//    private static CountDownLatch countDownLatch = new CountDownLatch(1);


    public void ExternalCall(List<Runnable> runnable) throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(8);

        runnable.forEach(item-> {
            executorService.execute(item);
        });

        
        System.out.println(" Other Work start !!  TODO:: ");
        
        shutdownAndAwaitTermination(executorService);

    }

    /**
     * 종료
     * @param pool
     */
    void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown(); // Disable new tasks from being submitted

        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }
}
