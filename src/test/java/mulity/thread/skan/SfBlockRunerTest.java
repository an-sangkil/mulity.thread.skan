package mulity.thread.skan;

import java.util.Calendar;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Test;

import mulity.thread.skan.model.User;
import mulity.thread.skan.test1.TestBlockingQ;
import mulity.thread.skan.thread.SfBlockRuner;

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
	public static void main(String[] args) {
		
		SfBlockRunerTest sfBlockRunerTest = SfBlockRunerTest.getInstance();
		//SfBlockRunerTest sfBlockRunerTest = new SfBlockRunerTest();
		SfBlockRuner<User> sf = (SfBlockRuner<User>) SfBlockRuner.getInstance();
		
		User user = new User();
		user.setUserId("user s");
		user.setUserName("user s");
		
		System.out.println("hihi~");
		//new SfBlockRuner<User>().setItem(user, queue);
		sf.setItem(user,queue );
		//sf.setItem(user,queue );
		sfBlockRunerTest.call0();
		sfBlockRunerTest.call1();
		sfBlockRunerTest.call2();
//		sfBlockRunerTest.call2();
		
		sf.runner(queue );
		
	}
	

	public void call0 () {
		
		User user = new User();
		user.setUserId("user0");
		user.setUserName("user0");
		
		SfBlockRuner<User> sf = (SfBlockRuner<User>) SfBlockRuner.getInstance();
		//new SfBlockRuner<User>().setItem(user, queue);
		sf.setItem(user,queue );
		//sf.runner(queue );
	}
	public void call1 () {
		
		User user = new User();
		user.setUserId("user1");
		user.setUserName("user1");
		
		
		SfBlockRuner<User> sf = (SfBlockRuner<User>) SfBlockRuner.getInstance();
		
		//new SfBlockRuner<User>().setItem(user, queue);
		sf.setItem(user,queue );
		sf.runner(queue );
	}
	
	public void call2 () {
		
		User user = new User();
		user.setUserId("user2");
		user.setUserName("user2");
		
		SfBlockRuner<User> sf = (SfBlockRuner<User>) SfBlockRuner.getInstance();
		
		//new SfBlockRuner<User>().setItem(user, queue);
		sf.setItem(user,queue );
		//sf.runner(queue );
	}
	
}
