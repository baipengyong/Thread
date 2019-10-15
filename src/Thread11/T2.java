package Thread11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T2 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0;i<6;i++){
          service.execute(()->{
              try {
                  TimeUnit.MILLISECONDS.sleep(500);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              System.out.println(Thread.currentThread().getName());
          });
        }

        System.out.println(service);

        //关闭线程池
        service.shutdown();
        //关闭线程池后判断所有任务是否都已完成
        System.out.println(service.isTerminated());
        //线程池是否关闭
        System.out.println(service.isShutdown());
    }
}
