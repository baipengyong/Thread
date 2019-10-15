package Thread06;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T2 {
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
        boolean b = false;
        try {
          b = lock.tryLock(11, TimeUnit.SECONDS);//11秒内尝试锁定对象
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (b){
            System.out.println("m2.....尝试锁定成功");
        }
        if(b){lock.unlock();}//判断确定拿到锁后再释放锁
    }


    public static void main(String[] args) {
        T2 t = new T2();

        new Thread(t::m1).start();

        new Thread(t::m2).start();
    }
}
