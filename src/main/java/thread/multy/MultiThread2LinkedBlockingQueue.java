package thread.multy;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import mulity.thread.model.User;

/**
 * <pre>
 * Class Name  : MulityThread1.java
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
 *          Copyright (C) 2014 by SKAN.COMPANY All right reserved.
 */
public class MultiThread2LinkedBlockingQueue {

	static  ThreadPoolExecutor threadPool = new ThreadPoolExecutor(4, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(4));

	public static void main(String[] args) {

		// 객체를 이용한 단순 LinkedBlockingQueue 사용법
		//  * 웹서버에서 사용할때
		// 	  (1) 웹에서 사용하고 싶을땐 Shutdown 로직을 제거한다.
		//    (2) Request 된 곳에서 execute 를 실행하고, 서버는 사용 가능한 Thread 수만큼 데이터를 처리한다.
		//  * LinkedBlockingQueue 를 사용하기 때문에 Queue 가 꽉차면 오류발생
		//    (1) 임계 영역이 존재하며, 기본 INTEGER.MAX 로 설정됨
		//	  (2) 블로킹 큐가 링크드 노드로 연결되어 있으며, 큐에서 원소를 꺼내갈것이 없을경우 wait 상태로 들어간다.
		//    (3) 생산자가 많고 소비가 하나의 경우에 사용하면 좋다.

		for (int i = 0; i < 10; i++) {
			threadPool.execute(new User("name", String.valueOf(i)));
		}
		
		threadPool.shutdown();

	}
}
