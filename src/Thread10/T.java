package Thread10;
/*
java中容器很多，在多线程下要适情况选择map/set

(1)不需要多线程
HashMap
TreeMap
Linkedhashmap

(2)少量并发
Hashtable
Collections.synchronizedList(list);

(3)高并发情况
ConcurrentHashMap
ConcurrentSkipListMap(带排序)

容器中选择用“队列”形式
(1)不需要多线程
ArrayList
LinkedList

(2)少量并发
Collections.synchronizedList(list);
CopyonWriteList(适用于写入少读取多的情况)

(3)高并发情况
CocurrentLinkedQueue
BlockingQueue(阻塞容器)
DelayQueue(定时任务容器)
*/

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

public class T {
    public static void main(String[] args) {

        Queue queue = (Queue) new LinkedList<>();
        //同步容器
        //Map<String,String> map = new HashMap<>();
        //Map<String,String> map = new Hashtable<>();
        Map<String,String> map = new ConcurrentSkipListMap<>();//跳表有序集合

        //线程数组，方便启动管理
        Thread[] ths = new Thread[100];
        //门闩
        CountDownLatch latch = new CountDownLatch(ths.length);
        //随机字符串对象
        Random r = new Random();
        //开始时间
        long start = System.currentTimeMillis();

        //初始化线程数组
        for (int i=0;i<ths.length;i++){
            ths[i] = new Thread(()->{
                for (int j=0;j<1000;j++){
                    map.put("key:"+r.nextInt(100000),"value"+r.nextInt(100000));
                }
                latch.countDown();//门闩-1;
            });
        }

        Arrays.asList(ths).forEach(t->t.start());//遍历线程数组启动

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //结束时间
        Long end = System.currentTimeMillis();
        //用时多少？
        System.out.println(end-start);


    }
}
