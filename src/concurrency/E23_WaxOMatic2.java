package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
class Car1 {
    private boolean waxOn;

    public synchronized void waxed() {
        waxOn = true; // Ready to buff
        notify();
    }

    public synchronized void buffed() {
        waxOn = false; // Ready for another coat of wax
        notify();
    }

    public synchronized void waitForWaxing()
            throws InterruptedException {
        while ( waxOn == false )
            wait();
    }

    public synchronized void waitForBuffing()
            throws InterruptedException {
        while ( waxOn == true )
            wait();
    }
}

class WaxOn implements Runnable {
    private Car1 car;

    public WaxOn(Car1 c) {
        car = c;
    }

    public void run() {
        try {
            while ( !Thread.interrupted() ) {
                printnb("Wax On! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch ( InterruptedException e ) {
            print("Exiting via interrupt");
        }
        print("Ending Wax On task");
    }
}

class WaxOff implements Runnable {
    private Car1 car;

    public WaxOff(Car1 c) {
        car = c;
    }

    public void run() {
//        Thinking in Java, 4 th Edition Annotated Solution Guide  632
        try {
            while ( !Thread.interrupted() ) {
                car.waitForWaxing();
                printnb("Wax Off! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch ( InterruptedException e ) {
            print("Exiting via interrupt");
        }
        print("Ending Wax Off task");
    }
}

public class E23_WaxOMatic2 {
    public static void main(String[] args) throws Exception {
        Car1 car = new Car1();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}