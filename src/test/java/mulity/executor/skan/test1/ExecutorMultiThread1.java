package mulity.executor.skan.test1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorMultiThread1 implements Callable<Integer> {
	
	private int total;
	
	public Integer call() throws Exception {
		for (int i = 1; i <= 20000; i++) {
			total += i;
		}
		Thread.sleep(2000);
		return total;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		Future<Integer> f1 = executorService.submit(new ExecutorMultiThread1());
		Future<Integer> f2 = executorService.submit(new ExecutorMultiThread1());
		Future<Integer> f3 = executorService.submit(new ExecutorMultiThread1());
		Future<Integer> f4 = executorService.submit(new ExecutorMultiThread1());
		Future<Integer> f5 = executorService.submit(new ExecutorMultiThread1());
		Future<Integer> f6 = executorService.submit(new ExecutorMultiThread1());
		System.out.printf("[%s] 1에서 20000까지의 총 합은 %d입니다.%n", "f1", f1.get());
		System.out.printf("[%s] 1에서 20000까지의 총 합은 %d입니다.%n", "f2", f2.get());
		System.out.printf("[%s] 1에서 20000까지의 총 합은 %d입니다.%n", "f3", f3.get());
		
		System.out.printf("[%s] 1에서 20000까지의 총 합은 %d입니다.%n", "f4", f4.get());
		System.out.printf("[%s] 1에서 20000까지의 총 합은 %d입니다.%n", "f5", f5.get());
		System.out.printf("[%s] 1에서 20000까지의 총 합은 %d입니다.%n", "f6", f6.get());
		executorService.shutdown();
	}
}
