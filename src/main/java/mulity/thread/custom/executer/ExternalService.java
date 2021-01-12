package mulity.thread.custom.executer;

import java.util.ArrayList;
import java.util.List;

import mulity.thread.custom.executer.task.LoopTask;

/**
 * <pre>
 * Description : NonblockE
 * @author mezzomedia
 * @since 2018.07.18
 * @version
 *
 * Copyright (C) 2018 by Mezzomedia.Inc. All right reserved.
 */
public class ExternalService {

    public static void main(String args[]) throws Exception {


        System.out.println("============== START ");
        ServiceRunner es = new ServiceRunner();

        Thread thread = new Thread() {
            @Override
            public void run() {


                List<Runnable> runnables = new ArrayList<>();
                //synchronized (rt) {
                //System.out.println("완료 될때까지 대기 ");
                for (int i = 0; i < 10; i++) {
                    runnables.add(new LoopTask());
                }
                //}

                try {
                    es.ExternalCall(runnables);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };

        thread.start();


        System.out.println("============== END ");

    }

}
