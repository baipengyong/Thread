package Thread06;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T {
    //替代synchronized
    Lock lock = new ReentrantLock();
    public void m1(){
                try {
                    lock.lock();//相同于synchronized（this）
                    for (int i=0;i<10;i++){
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();//手动释放锁
                }

    }


    public void m2(){
        lock.lock();
        System.out.println("m2.....");
        lock.unlock();
    }


    public static void main(String[] args) {
        T t = new T();

        new Thread(t::m1).start();

        new Thread(t::m2).start();
    }
}
