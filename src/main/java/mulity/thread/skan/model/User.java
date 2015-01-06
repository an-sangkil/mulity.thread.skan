package mulity.thread.skan.model;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * <pre>
 * Class Name  : User.java
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
public class User implements Runnable {
	
	private String userName;
	private String userId;

	/**
	 * 
	 */
	public User() {
		super();
	}
	public User(String string, String string2) {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [userName=" + userName + ", userId=" + userId + "]";
	}
	public void run() {
		try {
			System.out.println( Thread.currentThread().getName() +"[" + userId + "::" + userName);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
