package Thread04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class T {

    static AtomicInteger count = new AtomicInteger();
    public void m(){
        for (int i = 0;i<1000;i++) {
            count.incrementAndGet();//count++
        }
    }


    public static void main(String []ages){
        T t = new T();

        List<Thread> list = new ArrayList<>();

        for (int i = 0;i<10;i++){
            list.add(new Thread(t::m,"线程"+i));
        }

        list.forEach((o)->o.start());//启动new的十个线程


        list.forEach((o)->{
            try {
                o.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        System.out.println(count);

    }
}
