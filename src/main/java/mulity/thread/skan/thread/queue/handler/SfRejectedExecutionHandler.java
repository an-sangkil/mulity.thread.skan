package mulity.thread.skan.thread.queue.handler;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class SfRejectedExecutionHandler implements RejectedExecutionHandler {
	
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		// TODO Auto-generated method stub
		System.out.println(r.toString());
		
	}
	

}
