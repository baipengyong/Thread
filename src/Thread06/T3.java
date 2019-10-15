package Thread06;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T3 {

    Lock lock = new ReentrantLock();

    //模拟锁定对象后死循环占用资源
    public void m1(){
        lock.lock();
        try {
            while (true){

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


    public void m2(){
        try {
            //lockInterruptibly()也是锁定对象但是在执行期间是可打断的线程，只对interrupt()做出响应
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        T3 t3 = new T3();
        Thread t1 = new Thread(t3::m1);
        t1.start();

        Thread t2 = new Thread(t3::m2);
        t2.start();

        t2.interrupt();//主线程打断线程某一子线程

    }
}
