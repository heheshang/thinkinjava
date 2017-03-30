package concurrency;

/**
 * Created by Administrator on 2017/3/30 0030.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

//Concurrency  695
class ActiveCar {
	private final WaxingTask waxingTask = new WaxingTask();
	private final BuffingTask buffingTask = new BuffingTask();
	private ExecutorService ex = Executors.newSingleThreadExecutor();
	private Action lastAction = Action.BUFF;
	private boolean waxOn;

	private static void pause(int sleep_time) {
		try {
			TimeUnit.MILLISECONDS.sleep(sleep_time);
		} catch ( InterruptedException e ) {
			print("sleep() interrupted");
		}
	}

	public void wax() {
		try {
			ex.execute(waxingTask);
		} catch ( RejectedExecutionException e ) {
			System.out.println("wax RejectedExecutionException");
		}
	}

	public void buff() {
		try {
			ex.execute(buffingTask);
		} catch ( RejectedExecutionException e ) {
			System.out.println("buff RejectedExecutionException");
		}
	}

	public void shutdown() {
		ex.shutdown();
	}

	private enum Action {WAX, BUFF}

	private class WaxingTask implements Runnable {
		public void run() {
			if ( lastAction != Action.WAX ) {
				printnb("Wax On! ");
				pause(200);
				waxOn = true;
				lastAction = Action.WAX;
			}
		}
	}

	private class BuffingTask implements Runnable {
		public void run() {
			if ( lastAction != Action.BUFF ) {
				printnb("Wax Off! ");
				pause(200);
				waxOn = false;
				lastAction = Action.BUFF;
			}
		}
	}
//	Thinking in Java, 4 th Edition Annotated Solution Guide  696
}

class WaxCar implements Runnable {
	private final ActiveCar car;

	public WaxCar(ActiveCar c) {
		car = c;
	}

	public void run() {
		car.wax();
	}
}

class BuffCar implements Runnable {
	private final ActiveCar car;

	public BuffCar(ActiveCar c) {
		car = c;
	}

	public void run() {
		car.buff();
	}
}

public class E42_ActiveObjectWaxOMatic {
	public static void main(String[] args) throws Exception {
		ActiveCar car = new ActiveCar();
		ScheduledExecutorService exec = Executors.newScheduledThreadPool(2);
		exec.scheduleAtFixedRate(new BuffCar(car), 0, 200, TimeUnit.MILLISECONDS);
		exec.scheduleAtFixedRate(new WaxCar(car), 0, 200, TimeUnit.MILLISECONDS);
		TimeUnit.SECONDS.sleep(5); // Run for a while...
		exec.shutdownNow(); // Interrupt all tasks
		car.shutdown();
	}
}
/*

Here, the Active Object Car accepts two kinds of messages: wax and buff.
WaxCar and BuffCar serialize their message requests to run in parallel, so
they need no locks.
The output shows that even after WaxCar and BuffCar “stop,” ActiveCar still
handles the previously posted messages. New messages arrive faster than the
active object can handle them, so we take some precautions to avoid running out
of memory.
You must catch the RejectedExecutionException inside wax( ) and buff( )
(the methods accepting messages), because the client programmer needs to
interrogate the return value to know if the message has been accepted or
rejected.
 */