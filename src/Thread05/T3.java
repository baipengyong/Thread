package Thread05;
/*
淘宝面试题
需求：创建两个线程一个向集合中添加任意东西，另个线程监视线程1到添加第五个做出回应（追求完美）
*/

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class T3 {

    private List list = new ArrayList();

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        T3 c = new T3();

        //1、表示门闩关闭0、表示门闩开启
        CountDownLatch latch = new CountDownLatch(1);
        //栅栏
        CyclicBarrier cyclicBarrier;
        Semaphore semaphore;



        final Object o = new Object();

        new Thread(()->{
            System.out.println("t1启动");
                if (c.size()!=5){
                    try {
                        //无需锁定对象，让线程进入等待状态
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t1结束");
            }
        ).start();


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        new Thread(()->{
                for (int i = 0;i<10;i++){
                    c.add(new Object());
                    System.out.println("add" + i);

                    if (c.size() == 5) {
                        //唤醒等待的线程调用一次门闩-1直到为0表示门闩开启，该线程也可以继续执行
                        latch.countDown();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            System.out.println("t2结束");
        }).start();
    }
}
