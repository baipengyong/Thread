package Thread09;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class T3 {

    //队列实现并发容器
    static Queue list = new ConcurrentLinkedQueue();

    static {
        for (int i= 0;i<1000;i++){
            list.add("票号："+i);
        }
    }


    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            new Thread(()->{
             while (true){
                 String s = (String) list.poll();
                 if (s == null) {
                     break;
                 }else {
                     System.out.println("销售了："+s);
                 }
             }
            }).start();
        }
    }
}
