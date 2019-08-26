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
            countDown.await();//进入等待，等待主线程执行完毕，获得开始执行信号...
            System.out.println("处于等待的线程开始自己预期工作......");
            await.countDown();//完成预期工作，进行减1，发出完成信号...
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
    	CountDownLatch countDown = new CountDownLatch(1);
        CountDownLatch await = new CountDownLatch(5);
 
        // 依次创建并启动处于等待状态的5个MyRunnable线程
        for (int i = 0; i < 5; ++i) {
            new Thread(new MyRunnable(countDown, await)).start();
            //每个线程启动，每个线程一执行，countDown.await()，从而进入等待状态
        }
 
        System.out.println("用于触发处于等待状态的线程开始工作......");
        System.out.println("用于触发处于等待状态的线程工作完成，等待状态线程开始工作......");
        countDown.countDown();//主线程进行countDown，其他线程可以进行执行
        System.out.println(1);
        System.out.println(2);
        System.out.println(3);
        System.out.println(4);
        System.out.println(5);
        await.await();//阻塞后面，必须每个线程体  await.countDown(),将数值down至0才可以
        System.out.println("Bingo!");

	}
	
}
