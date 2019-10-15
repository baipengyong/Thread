package Thread11;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.*;

public class T3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask(()->{
            TimeUnit.MILLISECONDS.sleep(500);
            return 1000;
        });

        //启动
        new Thread(task).start();

        //阻塞等待执行结束获取
        System.out.println(task.get());


        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<Integer> f = service.submit(()->{
            TimeUnit.MILLISECONDS.sleep(500);
            return 1;
        });
        //线程返回值
        System.out.println(f.get());
        //执行完了吗？
        System.out.println(f.isDone());
    }
}
