package thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2019 by skan. All right reserved.
 * @since 2019-01-17
 */
public class CountDownLatchExample {
  static final int max = 3;

  public static void testCountDownLatch() throws Exception {
    final CountDownLatch latch = new CountDownLatch(max);

    for (long i = 1; i <= max; i++) {
      new Thread(new Worker(latch, i * 100)).start();
    }

    latch.await();

    System.out.println("########### CountDownLatch End ###########");
  }

  static class Worker implements Runnable {
    private CountDownLatch latch;
    private long n;

    public Worker(CountDownLatch latch, long n) {
      this.latch = latch;
      this.n = n;
    }

    @Override
    public void run() {
      try {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
          cnt++;
        }

        System.out.println(cnt);
      } catch (Exception ex) {
        ex.printStackTrace();
      } finally {
        if (this.latch == null) {
          return;
        }

        latch.countDown();
      }
    }
  }

  public static void main(String[] args) throws Exception {
    System.out.println("start");
    testCountDownLatch();
    System.out.println("end");
  }

  /**
   * start
   * 200
   * 100
   * 300
   * ########### CountDownLatch End ###########
   * end
   */
}