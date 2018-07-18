package mulity.thread.skan;

import java.util.Calendar;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Test;

import mulity.thread.skan.model.User;
import mulity.thread.skan.test1.TestBlockingQ;
import mulity.thread.skan.thread.queue.SfBlockRuner;
import mulity.thread.skan.utils.StateUtils;

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
public class SfBlockRunerTest {
	
	
	public static volatile SfBlockRunerTest INSTANCE;
	
	public static SfBlockRunerTest getInstance() {
		
		if(INSTANCE == null ) {
			synchronized (SfBlockRunerTest.class) {
				INSTANCE = new SfBlockRunerTest();
			}
		}
	
		return INSTANCE;
	}
	
	//static BlockingQueue<User> queue = new ArrayBlockingQueue<User>(4);
	static BlockingQueue<User> queue = new LinkedBlockingQueue<User>(2);
	//@SuppressWarnings("unchecked")
	//static SfBlockRuner<User> sf = (SfBlockRuner<User>) SfBlockRuner.getInstance();
	
	//@Test
	//public void testBlocking () {
	public static void main(String[] args) throws InterruptedException {
		
		SfBlockRunerTest sfBlockRunerTest = SfBlockRunerTest.getInstance();
		//SfBlockRunerTest sfBlockRunerTest = new SfBlockRunerTest();
		//SfBlockRuner<User> sf = (SfBlockRuner<User>) SfBlockRuner.getInstance();
		
		User user = new User();
		user.setUserId("user s");
		user.setUserName("user s");
		
		//new SfBlockRuner<User>().setItem(user, queue);
		//sf.setItem(user,queue );
		//System.out.println("1 = " + queue.size());
		sfBlockRunerTest.call0("user0" ,"user0",1);
		//System.out.println("2 = " + queue.size());
		sfBlockRunerTest.call0("user1" ,"user1",2);
		//System.out.println("3 = " + queue.size());
		sfBlockRunerTest.call0("user2" ,"user2",3);
		//System.out.println("4 = " + queue.size());
		sfBlockRunerTest.call0("user3" ,"user3",4);
		//System.out.println("5 = " + queue.size());
		sfBlockRunerTest.call0("user5" ,"user5",5);

		//sf.runner(queue );
		
	}
	

	public void call0 (String userId, String userName,int key) throws InterruptedException {
		
		User user = new User();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setKey(key);
		
		SfBlockRuner<User> sf = (SfBlockRuner<User>) SfBlockRuner.getInstance();
		sf.setItem(user,queue );
		
		StateUtils stateUtils = StateUtils.getInstance();
		
		stateUtils.putItem(key, user);
		//System.out.println(queue.size());
		sf.runner(queue );
	}
	
	
}
