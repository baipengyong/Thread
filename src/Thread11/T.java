package Thread11;

import java.util.concurrent.Executor;

public class T implements Executor {

    @Override
    public void execute(Runnable command) {
        command.run();
    }


    public static void main(String[] args) {
        T t = new T();
        t.execute(()->System.out.println("hello Executor"));
    }
}
