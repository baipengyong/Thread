package Thread09;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

/*
模拟火车售票系统多线程抢票
*/
public class T {
    //该集合内所有方法几乎都是同步方法(同步容器)
    static Vector list = new Vector();

    static {
        for (int i= 0;i<1000;i++){
            list.add("票号："+i);
        }
    }


    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            new Thread(()->{
                //该程序判断语句与执行语句之间不具备原子性，会造成其他线程进入
                while (list.size()>0){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("已出售："+list.remove(0));
                }
            }).start();
        }
    }
}
