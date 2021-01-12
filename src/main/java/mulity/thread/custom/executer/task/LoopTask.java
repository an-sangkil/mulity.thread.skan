package mulity.thread.custom.executer.task;

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
public class LoopTask implements Runnable {

    @Override
    public void run() {
        //synchronized(this) {

        try {

            System.out.println(Thread.currentThread().getName() + "thread Executor LoopTask= " + Thread.currentThread().getId());

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // notify();
        //}
    }

    public void methodTask() throws Exception {


        //System.out.println("test Run End");
    }


}
