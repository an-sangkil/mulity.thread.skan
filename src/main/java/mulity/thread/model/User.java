package mulity.thread.model;

import java.util.concurrent.TimeUnit;

import javax.sound.midi.Synthesizer;


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
 * @version Copyright (C) 2014 by SKAN.COMPANY All right reserved.
 * @since 2014. 12. 17.
 */
public class User implements Runnable {

    private String userName;
    private String userId;
    private int key;

    public User() {
        super();
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

      public User(String string, String string2) {
        // TODO Auto-generated constructor stub
        System.out.println(string + string2);

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User [userName=" + userName + ", userId=" + userId + ", key=" + key + "]";
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            System.out.println(Thread.currentThread().getName() + "thread Executor LoopTask= " + Thread.currentThread().getId());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
