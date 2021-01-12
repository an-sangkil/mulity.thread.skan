package thread.pubsub;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 데이터 합산 작업 Thread
 *  - 총 2초가 걸리 하나의 작업들 ,  9 개의 작업수행시 18초가 수행되어야 하나.  3개의 Thread 를 사용하여  6초안에 실행
 *  (1) 3개의 Thread 를 사용하여, 2초 단위로
 */
public class ExecutorMultiThreadExample implements Callable<Integer> {
	
	private int total;
	
	public Integer call() throws Exception {
		for (int i = 1; i <= 20000; i++) {
			total += i;
		}
		Thread.sleep(2000);
		return total;
	}

	public static void main(String[] args) throws Exception {
		//multi();
		nonMulti();
	}

	public static void multi() throws Exception{
		ExecutorService executorService = Executors.newFixedThreadPool(3);

		Future<Integer> f1 = executorService.submit(new ExecutorMultiThreadExample());
		Future<Integer> f2 = executorService.submit(new ExecutorMultiThreadExample());
		Future<Integer> f3 = executorService.submit(new ExecutorMultiThreadExample());

		Future<Integer> f4 = executorService.submit(new ExecutorMultiThreadExample());
		Future<Integer> f5 = executorService.submit(new ExecutorMultiThreadExample());
		Future<Integer> f6 = executorService.submit(new ExecutorMultiThreadExample());

		Future<Integer> f7 = executorService.submit(new ExecutorMultiThreadExample());
		Future<Integer> f8 = executorService.submit(new ExecutorMultiThreadExample());
		Future<Integer> f9 = executorService.submit(new ExecutorMultiThreadExample());

		System.out.printf("[%s] 1에서 20000까지의 총 합은 %d입니다.%n", "f1", f1.get());
		System.out.printf("[%s] 1에서 20000까지의 총 합은 %d입니다.%n", "f2", f2.get());
		System.out.printf("[%s] 1에서 20000까지의 총 합은 %d입니다.%n", "f3", f3.get());

		System.out.printf("[%s] 1에서 20000까지의 총 합은 %d입니다.%n", "f4", f4.get());
		System.out.printf("[%s] 1에서 20000까지의 총 합은 %d입니다.%n", "f5", f5.get());
		System.out.printf("[%s] 1에서 20000까지의 총 합은 %d입니다.%n", "f6", f6.get());

		System.out.printf("[%s] 1에서 20000까지의 총 합은 %d입니다.%n", "f7", f7.get());
		System.out.printf("[%s] 1에서 20000까지의 총 합은 %d입니다.%n", "f8", f8.get());
		System.out.printf("[%s] 1에서 20000까지의 총 합은 %d입니다.%n", "f9", f9.get());

		executorService.shutdown();

	}
	public static void nonMulti() throws Exception {
		int f1 = new ExecutorMultiThreadExample().call();
		System.out.printf("[%s] 1에서 20000까지의 총 합은 %d입니다.%n", "f1", f1);
		int f2 = new ExecutorMultiThreadExample().call();
		System.out.printf("[%s] 1에서 20000까지의 총 합은 %d입니다.%n", "f2", f1);
		int f3 = new ExecutorMultiThreadExample().call();
		System.out.printf("[%s] 1에서 20000까지의 총 합은 %d입니다.%n", "f3", f1);
		new ExecutorMultiThreadExample().call();
		new ExecutorMultiThreadExample().call();
		new ExecutorMultiThreadExample().call();
		new ExecutorMultiThreadExample().call();
		new ExecutorMultiThreadExample().call();
		int f9 = new ExecutorMultiThreadExample().call();
		System.out.printf("[%s] 1에서 20000까지의 총 합은 %d입니다.%n", "f9", f1);
	}
}
