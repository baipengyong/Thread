package Thread05;
/*
淘宝面试题
需求：创建两个线程一个向集合中添加任意东西，另个线程监视线程1到添加第五个做出回应（追求完美）
*/

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T {

    //两个线程之间的属性可见
    volatile List list = new ArrayList();

    //遗留问题：t2线程死循环浪费资源

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        T c = new T();

        new Thread(()->{
            for (int i=0;i<10;i++){
                c.add(new Object());
                System.out.println("add"+i);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        ).start();




        new Thread(()->{
            while (true){
                if (c.size()==5){
                    break;
                }
            }
            System.out.println("t2结束");
        }).start();
    }
}
