package Thread03;

import java.util.concurrent.TimeUnit;

public class T {
    volatile boolean running = true;


    public void m1(){
        System.out.println("死循环开始！");
        while (running){

            //让处理器有空闲时间，处理器就会自动从主存中重新读取
//            try {
//                TimeUnit.SECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        System.out.println("死循环结束！");
    }


    public static void main(String []ages){

        T t = new T();
        new Thread(t::m1).start();

        //主线程沉睡一会
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.running = false;
    }
}
