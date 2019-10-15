package Thread05;
/*
淘宝面试题
需求：创建两个线程一个向集合中添加任意东西，另个线程监视线程1到添加第五个做出回应（追求完美）
*/

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T2 {

    List list = new ArrayList();

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        T2 c = new T2();

        final Object o = new Object();

        new Thread(()->{
            synchronized (o){
                if (c.size()!=5){
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t1结束");
                o.notify();
            }
        }
        ).start();


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        new Thread(()->{
            synchronized (o){
                for (int i = 0;i<10;i++){
                    c.add(new Object());
                    System.out.println("add" + i);

                    if (c.size() == 5) {
                        //唤醒等待的线程
                        o.notify();
                        //释放对象锁
                        try {
                            o.wait();
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }

            System.out.println("t2结束");
        }).start();
    }
}
