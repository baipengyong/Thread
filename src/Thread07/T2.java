package Thread07;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
精确调用生产者、消费者
*/
public class T2 {
    private LinkedList linkedList = new LinkedList();
    final private int MAX=10;
    private int count = 0;

    private Lock lock = new ReentrantLock();
    //条件
    private Condition producer = lock.newCondition();//生产者条件
    private Condition consumer = lock.newCondition();//消费者条件

    public void put(Object o){
       lock.lock();
       while (linkedList.size() == MAX){
           try {
               producer.await();//该while条件满足所有生产者在让出资源等待
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }

       linkedList.add(o);
       ++count;
       consumer.signalAll();//唤醒所有消费者线程
       lock.unlock();
    }




    public synchronized Object get(){
       lock.lock();

       while (linkedList.size()==0){
           try {
               consumer.await();//该while条件满足所有消费者在让出资源等待
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }

       Object o = linkedList.removeFirst();
       --count;
       lock.unlock();
       return o;
    }


    public static void main(String[] args) {
        T2 t = new T2();

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
                    t.put(Thread.currentThread().getName()+" "+j);
                }
            },"p"+i).start();
        }

    }
}
