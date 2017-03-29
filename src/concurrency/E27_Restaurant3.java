package concurrency;

/**
 * Created by Administrator on 2017/3/28 0028.
 */


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

class WaitPerson3 implements Runnable {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    private Restaurant3 restaurant;

    public WaitPerson3(Restaurant3 r) {
        restaurant = r;
    }

    public void run() {
        try {
            while ( !Thread.interrupted() ) {
                lock.lock();
                try {
                    while ( restaurant.meal == null )
                        condition.await();
                } finally {
                    lock.unlock();
                }
                print("Waitperson got " + restaurant.meal);
                restaurant.chef.lock.lock();
                try {
                    restaurant.meal = null;
                    restaurant.chef.condition.signalAll();
                } finally {
                    restaurant.chef.lock.unlock();
                }
            }
        } catch ( InterruptedException e ) {
            print("WaitPerson interrupted");
        }
    }
}

class Chef3 implements Runnable {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    private Restaurant3 restaurant;
    private int count;

    public Chef3(Restaurant3 r) {
        restaurant = r;
    }

    public void run() {
        try {
            while ( !Thread.interrupted() ) {
                lock.lock();
                try {
                    while ( restaurant.meal != null )
                        condition.await();
                } finally {
                    lock.unlock();
//                    Thinking in Java, 4 th Edition Annotated Solution Guide  642
                }
                if ( ++count == 10 ) {
                    print("Out of food, closing");
                    restaurant.exec.shutdownNow();
                }
                printnb("Order up! ");
                restaurant.waitPerson.lock.lock();
                try {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.condition.signalAll();
                } finally {
                    restaurant.waitPerson.lock.unlock();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch ( InterruptedException e ) {
            print("Chef interrupted");
        }
    }
}

class Restaurant3 {
    Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson3 waitPerson = new WaitPerson3(this);
    Chef3 chef = new Chef3(this);

    public Restaurant3() {
        exec.execute(chef);
        exec.execute(waitPerson);
    }
}

public class E27_Restaurant3 {
    public static void main(String[] args) {
        new Restaurant3();
    }
} /* Output: (Sample)
Order up! Waitperson got Meal 1
Order up! Waitperson got Meal 2
Order up! Waitperson got Meal 3
Order up! Waitperson got Meal 4
Order up! Waitperson got Meal 5
Order up! Waitperson got Meal 6
Order up! Waitperson got Meal 7
Order up! Waitperson got Meal 8
Order up! Waitperson got Meal 9
Out of food, closing
Concurrency  643
WaitPerson interrupted
Order up! Chef interrupted
*///:~