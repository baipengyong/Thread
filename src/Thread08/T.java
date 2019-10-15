package Thread08;

import java.util.concurrent.TimeUnit;

/*
线程局部变量
测试证明：ThreadLocal同一个对象中数据只能由添加线程获取，其他线程无法获取
*/
public class T {
    static ThreadLocal<Object> tl= new ThreadLocal<>();

    public static void main(String[] args) {

        //定义一个线程ThreadLocal获取值
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get());
        }).start();

        //定义一个线程ThreadLocal添加值
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Object());
        }).start();
    }
}
