package yxxy.c_019;

import java.util.concurrent.CountDownLatch;

public class MyRunnable implements Runnable{
	private final CountDownLatch countDown;
    private final CountDownLatch await;
 
    public MyRunnable(CountDownLatch countDown, CountDownLatch await) {
        this.countDown = countDown;
        this.await = await;
    }
 
    public void run() {
        try {
            countDown.await();//����ȴ����ȴ����߳�ִ����ϣ���ÿ�ʼִ���ź�...
            System.out.println("���ڵȴ����߳̿�ʼ�Լ�Ԥ�ڹ���......");
            await.countDown();//���Ԥ�ڹ��������м�1����������ź�...
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
    	CountDownLatch countDown = new CountDownLatch(1);
        CountDownLatch await = new CountDownLatch(5);
 
        // ���δ������������ڵȴ�״̬��5��MyRunnable�߳�
        for (int i = 0; i < 5; ++i) {
            new Thread(new MyRunnable(countDown, await)).start();
            //ÿ���߳�������ÿ���߳�һִ�У�countDown.await()���Ӷ�����ȴ�״̬
        }
 
        System.out.println("���ڴ������ڵȴ�״̬���߳̿�ʼ����......");
        System.out.println("���ڴ������ڵȴ�״̬���̹߳�����ɣ��ȴ�״̬�߳̿�ʼ����......");
        countDown.countDown();//���߳̽���countDown�������߳̿��Խ���ִ��
        System.out.println(1);
        System.out.println(2);
        System.out.println(3);
        System.out.println(4);
        System.out.println(5);
        await.await();//�������棬����ÿ���߳���  await.countDown(),����ֵdown��0�ſ���
        System.out.println("Bingo!");

	}
	
}
