高并发容器
总结：
1：对于map/set的选择使用
HashMap
TreeMap
LinkedHashMap

//并发量小
Hashtable
Collections.sychronizedXXX

//并发性高
ConcurrentHashMap      //并发性高
ConcurrentSkipListMap  //并发性高且排序

2：队列
ArrayList    //非同步队列
LinkedList

Collections.synchronizedXXX  //同步队列
CopyOnWriteList //写少读多

//高并发
Queue
	CocurrentLinkedQueue //concurrentArrayQueue
	BlockingQueue
		LinkedBQ
		ArrayBQ
		TransferQueue
		SynchronusQueue
	DelayQueue执行定时任务
		
	