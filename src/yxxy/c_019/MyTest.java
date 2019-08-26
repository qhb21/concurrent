package yxxy.c_019;
/**
 * 曾经的面试题：（淘宝？）
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * 
 * 分析下面这个程序，能完成这个功能吗？
 * @author mashibing 
 */
import java.util.ArrayList;

public class MyTest {
	//存放数据, volatile 保证 list 对象对所有线程
	private volatile ArrayList<Integer> list=new ArrayList<Integer>();
	
	public void add(int i){
		list.add(i);
	}
	
	public int size(){
		return list.size();
	}
	public static void main(String[] args) {
		MyTest myTest=new MyTest();
		final Object lock=new Object();//锁对象
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {//线程2监控线程1是否个数到5个
				synchronized (lock) {
					if (myTest.size()!=5) {
						try {
							lock.wait(); // 不满足条件，线程进入等待，释放锁
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					//如果满足条件，通知t1继续添加数据
					System.out.println("t2: 容器已经添加了5个元素");
					lock.notifyAll();//唤醒所有等待的进程，注意：不唤醒，其他线程不会抢夺所资源；notify()不会释放锁，但是这里线程体执行完毕会释放锁
				}
			}
		},"t2").start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					synchronized (lock) {
						myTest.add(i);
						System.out.println("add: "+i);
						if (myTest.size()==5) {
							lock.notifyAll();
							try {
								//添加了五个的时候，t1线程让出锁，让t2进行执行通知，t2执行完，再释放锁，t1获得锁，再继续添加数据
								lock.wait(); //wait()之后再调用notifyAll()无作用
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
						}
						
						try {
							Thread.sleep(1000);//是数据一秒一秒输出，方便观察
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		},"t1").start();
		
	}
}
