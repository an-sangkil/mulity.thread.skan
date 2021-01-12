package mulity.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import mulity.thread.custom.queue.BlockRunner;
import mulity.thread.model.User;
import mulity.thread.utils.StateUtils;

/**
 * <pre>
 * Class Name  : SfBlockRunerTest.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2014. 12. 17.        ask               최초생성
 * </pre>
 *
 * @author ask
 * @since 2014. 12. 17.
 * @version 
 *
 * Copyright (C) 2014 by SKAN.COMPANY All right reserved.
 */
public class CustomBlockRunnerTest {
	
	
	public static volatile CustomBlockRunnerTest INSTANCE;
	
	public static CustomBlockRunnerTest getInstance() {
		
		if(INSTANCE == null ) {
			synchronized (CustomBlockRunnerTest.class) {
				INSTANCE = new CustomBlockRunnerTest();
			}
		}
	
		return INSTANCE;
	}
	
	//static BlockingQueue<User> queue = new ArrayBlockingQueue<User>(4);
	static BlockingQueue<User> queue = new LinkedBlockingQueue<User>(2);


	public static void main(String[] args) throws InterruptedException {
		
		CustomBlockRunnerTest customBlockRunnerTest = CustomBlockRunnerTest.getInstance();

		User user = new User();
		user.setUserId("user s");
		user.setUserName("user s");
		
		//new SfBlockRuner<User>().setItem(user, queue);
		//sf.setItem(user,queue );
		//System.out.println("1 = " + queue.size());
		customBlockRunnerTest.call0("user0" ,"user0",1);
		//System.out.println("2 = " + queue.size());
		customBlockRunnerTest.call0("user1" ,"user1",2);
		//System.out.println("3 = " + queue.size());
		customBlockRunnerTest.call0("user2" ,"user2",3);
		//System.out.println("4 = " + queue.size());
		customBlockRunnerTest.call0("user3" ,"user3",4);
		//System.out.println("5 = " + queue.size());
		customBlockRunnerTest.call0("user5" ,"user5",5);
		customBlockRunnerTest.call0("user6" ,"user6",6);
		customBlockRunnerTest.call0("user7" ,"user7",7);
		customBlockRunnerTest.call0("user8" ,"user8",8);
		customBlockRunnerTest.call0("user9" ,"user9",9);

		//sf.runner(queue );
		
	}
	

	public void call0 (String userId, String userName,int key) throws InterruptedException {
		
		User user = new User();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setKey(key);
		
		BlockRunner<User> sf = (BlockRunner<User>) BlockRunner.getInstance();
		sf.setItem(user,queue );
		
		StateUtils stateUtils = StateUtils.getInstance();
		stateUtils.putItem(key, user);

		//System.out.println(queue.size());
		sf.runner(queue );
	}
	
	
}
