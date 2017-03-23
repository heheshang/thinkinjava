package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/3/23 0023.
 */
class AtomicityTest2 implements Runnable {
    private int i;

    public synchronized int getValue() {
        return i;
    }

    public synchronized void evenIncrement() {
        i++;
        i++;
    }

    @Override
    public void run() {
        while ( true )
            evenIncrement();
    }
}

public class E12_AtomicityTest2 {
    public static void main(String[] args) {
        System.out.println("Press Control-C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicityTest2 at = new AtomicityTest2();
        exec.execute(at);
        while ( true ) {
            int val = at.getValue();
            if ( val % 2 != 0 ) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}
/*
       The program now runs indefinitely, which might mean that it’s working
        correctly. But when you assess your program based on testing alone, you risk
        shipping incorrect code to the customer. Formal verification is necessary for
        concurrent programming.
        However, using some semi-formal verification, we can reasonably examine the
        program. Both methods are synchronized, and no race condition can occur.
        While one thread changes the state of an object, the other waits. Writes by one
        thread are always visible to the other. Accesses to the shared resource are fully
        serialized, with no visibility problems.
*///:~
