package yxxy.c_019;
/**
 * �����������⣺���Ա�����
 * ʵ��һ���������ṩ����������add��size
 * д�����̣߳��߳�1���10��Ԫ�ص������У��߳�2ʵ�ּ��Ԫ�صĸ�������������5��ʱ���߳�2������ʾ������
 * 
 * �����������������������������
 * @author mashibing 
 */
import java.util.ArrayList;

public class MyTest {
	//�������, volatile ��֤ list ����������߳�
	private volatile ArrayList<Integer> list=new ArrayList<Integer>();
	
	public void add(int i){
		list.add(i);
	}
	
	public int size(){
		return list.size();
	}
	public static void main(String[] args) {
		MyTest myTest=new MyTest();
		final Object lock=new Object();//������
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {//�߳�2����߳�1�Ƿ������5��
				synchronized (lock) {
					if (myTest.size()!=5) {
						try {
							lock.wait(); // �������������߳̽���ȴ����ͷ���
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					//�������������֪ͨt1�����������
					System.out.println("t2: �����Ѿ������5��Ԫ��");
					lock.notifyAll();//�������еȴ��Ľ��̣�ע�⣺�����ѣ������̲߳�����������Դ��notify()�����ͷ��������������߳���ִ����ϻ��ͷ���
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
								//����������ʱ��t1�߳��ó�������t2����ִ��֪ͨ��t2ִ���꣬���ͷ�����t1��������ټ����������
								lock.wait(); //wait()֮���ٵ���notifyAll()������
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
						}
						
						try {
							Thread.sleep(1000);//������һ��һ�����������۲�
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		},"t1").start();
		
	}
}
