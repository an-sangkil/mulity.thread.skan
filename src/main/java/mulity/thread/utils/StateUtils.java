package mulity.thread.utils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <pre>
 * Class Name  : StateUtils.java
 * Description : 
 * Modification Information
 * 
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2015. 1. 7.        ask               최초생성
 * </pre>
 *
 * @author ask
 * @since 2015. 1. 7.
 * @version
 *
 *          Copyright (C) 2014 by SKAN.COMPANY All right reserved.
 */
public class StateUtils {

	private static volatile StateUtils INSTANSE;
	
	private static volatile ConcurrentMap<Object, Object> concurrentMap = new ConcurrentHashMap<>();
	private StateUtils() {
	}

	public static StateUtils getInstance() {

		if (INSTANSE == null) {
			synchronized (StateUtils.class) {
				INSTANSE = new StateUtils();
			}
		}
		return INSTANSE;
	}
	
	public int getItemSize() {
		return concurrentMap.size();
	}
	
	public void putItem(int key,Object o) {
		concurrentMap.put(key, o);
	}
	
	public Object getItem(int key) {
		return concurrentMap.get(key);
	}
	
	public void removeItem(int key) {
		concurrentMap.remove(key);
	}

}
