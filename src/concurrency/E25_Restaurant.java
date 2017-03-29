package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
class Meal1 {
    private final int orderNum;

    public Meal1(int orderNum) {
        this.orderNum = orderNum;
    }

    public String toString() {
        return "Meal " + orderNum;
    }
}

class WaitPerson1 implements Runnable {
    private Restaurant1 restaurant;

    public WaitPerson1(Restaurant1 r) {
        restaurant = r;
    }

    public void run() {
        try {
            while ( !Thread.interrupted() ) {
                synchronized ( this ) {
                    while ( restaurant.meal == null )
                        wait(); // ... for the chef to produce a meal
//                    Thinking in Java, 4 th Edition Annotated Solution Guide  636
                }
                print("Waitperson got " + restaurant.meal);
                synchronized ( restaurant.chef ) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll(); // Ready for another
                }
            }
        } catch ( InterruptedException e ) {
            print("WaitPerson interrupted");
        }
    }


}

class Chef1 implements Runnable {
    private Restaurant1 restaurant;
    private int count = 0;

    public Chef1(Restaurant1 r) {
        restaurant = r;
    }

    public void run() {
        try {
            while ( !Thread.interrupted() ) {
                synchronized ( this ) {
                    while ( restaurant.meal != null )
                        wait(); // ... for the meal to be taken
                }
                if ( ++count == 10 ) {
                    print("Out of food, closing");
                    restaurant.exec.shutdownNow();
                    return;
                }
                printnb("Order up! ");
                synchronized ( restaurant.waitPerson ) {
                    restaurant.meal = new Meal1(count);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch ( InterruptedException e ) {
            print("Chef interrupted");
        }
    }
}

class Restaurant1 {
    Meal1 meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson1 waitPerson = new WaitPerson1(this);
    Chef1 chef = new Chef1(this);

    //    Concurrency  637
    public Restaurant1() {
        exec.execute(chef);
        exec.execute(waitPerson);
    }

    public static void main(String[] args) {
        new Restaurant1();
    }
}

public class E25_Restaurant {
    public static void main(String[] args) {
        new Restaurant1();
    }
}