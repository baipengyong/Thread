package Thread02;

public class T {

    int i = 10;

    public synchronized void m1(){
        //重入锁   锁上加锁（子类与父类相互都可以调用彼此的同步方法）
        m2();
        System.out.println("m1执行。。。");
    }

    public synchronized void m2(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i--;
        System.out.println(Thread.currentThread().getName()+"-m2:"+i);
    }


    public static void main(String []agrs){
        T t = new T();
        new Thread(t::m1,"线程1").start();

    }
}
