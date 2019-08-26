/**
  * synchronized浼樺寲
 * 鍚屾浠ｇ爜鍧椾腑鐨勮鍙ヨ秺灏戣秺濂�
 * 姣旇緝m1鍜宮2
 * @author mashibing
 */
package yxxy.c_016;

import java.util.concurrent.TimeUnit;


public class T {
	
	int count = 0;

	synchronized void m1() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		count ++;
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void m2() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		synchronized(this) {
			count ++;
		}
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	

}
