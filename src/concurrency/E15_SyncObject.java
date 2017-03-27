package concurrency;

import static net.mindview.util.Print.print;

/**
 * Created by Administrator on 2017/3/23 0023.
 */
class SingleSynch {
    public synchronized void f() {
        for ( int i = 0; i < 5; i++ ) {
            print("f()");
            Thread.yield();
        }
    }

    public synchronized void g() {
        for ( int i = 0; i < 5; i++ ) {
            print("g()");
            Thread.yield();
        }
    }

    public synchronized void h() {
        for ( int i = 0; i < 5; i++ ) {
            print("h()");
            Thread.yield();
        }
    }
}

class TripleSynch {
    private Object syncObjectG = new Object();
    private Object syncObjectH = new Object();

    public synchronized void f() {
        for ( int i = 0; i < 5; i++ ) {
            print("f()");
            Thread.yield();
        }
    }

    public void g() {
        synchronized ( syncObjectG ) {
            for ( int i = 0; i < 5; i++ ) {
                print("g()");
                Thread.yield();
            }
        }
    }

    public void h() {
        synchronized ( syncObjectH ) {
            for ( int i = 0; i < 5; i++ ) {
                print("h()");
                Thread.yield();
            }
        }
    }
}

public class E15_SyncObject {
    public static void main(String[] args) throws InterruptedException {
        final SingleSynch singleSynch = new SingleSynch();
        final TripleSynch tripleSynch = new TripleSynch();
        print("Test singleSynch...");
        Thread t1 = new Thread() {
            @Override
            public void run() {
                singleSynch.f();
            }
        };
        t1.start();

        Thread t2 = new Thread() {
            @Override
            public void run() {
                singleSynch.g();
            }
        };
        t2.start();
        singleSynch.h();
        t1.join();
        t2.join();


        print("Test TripleSynch...");
        new Thread() {
            @Override
            public void run() {
                tripleSynch.f();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                tripleSynch.g();
            }
        }.start();
        tripleSynch.h();
    }
}
