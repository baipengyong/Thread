package Thread07;

import java.util.LinkedList;

public class T {
    private LinkedList linkedList = new LinkedList();
    final private int MAX=10;
    private int count = 0;

    public synchronized void put(Object o){
        while (linkedList.size() == MAX){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        linkedList.add(o);
        ++count;
        this.notifyAll();//唤醒一个线程的话有可能还是生产者，直接唤醒所有阻塞线程
    }




    public synchronized Object get(){
        while (linkedList.size() == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Object o = linkedList.removeFirst();
        --count;
        this.notifyAll();
        return o;
    }


    public static void main(String[] args) {
        T t = new T();

        //先启动消费者
        for (int i=0;i<10;i++){
            new Thread(()->{
                for (int j=0;j<5;j++){
                    System.out.println(t.get());
                }
            },"c"+i).start();
        }


        //再启动生产者
        for (int i=0;i<2;i++){
            new Thread(()->{
                for (int j=0;j<25;j++){
                    t.put("内容："+Thread.currentThread().getName()+" "+j);
                }
            },"p"+i).start();
        }

    }
}
