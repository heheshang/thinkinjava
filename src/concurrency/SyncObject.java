package concurrency;//: concurrency/SyncObject.java
// Synchronizing on another object.

import java.util.ArrayList;

import static net.mindview.util.Print.print;

class DualSynch {
    private Object syncObject = new Object();

    public synchronized void f() {
        for ( int i = 0; i < 5; i++ ) {
            print("f()");
            Thread.yield();
        }
    }

    public void g() {
        synchronized ( syncObject ) {
            for ( int i = 0; i < 5; i++ ) {
                print("g()");
                Thread.yield();
            }
        }
    }
}

public class SyncObject {
    public static void main(String[] args) {
        final DualSynch ds = new DualSynch();
        new Thread() {
            public void run() {
                ds.f();
            }
        }.start();
        ds.g();
        ArrayList<String> strings1 = new ArrayList<>();
        strings1.add("1");
        strings1.add("2");
        strings1.add("3");
        strings1.add("4");
        strings1.forEach(System.out::println);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        strings.forEach(System.out::println);
    }
} /* Output: (Sample)
g()
f()
g()
f()
g()
f()
g()
f()
g()
f()
*///:~
