package Thread01;
//Thread Runnable 不需要导包因为在java.lang中
//同步方法与非同步方法运行彼此不影响
public class T implements Runnable{

    private static int i = 10;

//    非同步方法（普通方法）
    public /*synchronized*/  void m1(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i--;
        System.out.println(Thread.currentThread().getName()+"-m1-i:"+i);
    }

//    同步方法
    public synchronized void m2(){//相当于synchronized（this）
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i--;
        System.out.println(Thread.currentThread().getName()+"-m2-i:"+i);

    }

    @Override
    public void run() {
        m2();
    }

    public static void main(String []agrs){
        T t = new T();
        new Thread(t::m2,"线程1").start();
        new Thread(t::m1,"线程2").start();

//          java1.8中简化写法
//        new Thread(()->t.m1(),"线程1").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                t.m1();
//            }
//        });

    }


}

