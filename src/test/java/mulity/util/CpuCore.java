package mulity.util;

import org.junit.Test;

/**
 * <pre>
 * Class Name  : CpuCore.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2014. 12. 24.        ask               최초생성
 * </pre>
 *
 * @author ask
 * @since 2014. 12. 24.
 * @version 
 *
 * Copyright (C) 2014 by SKAN.COMPANY All right reserved.
 */
public class CpuCore {
	
	@Test
	public void testCpuCore() {
		System.out.println(Runtime.getRuntime().availableProcessors());
	}
}
