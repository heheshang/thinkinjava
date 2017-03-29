package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Created by Administrator on 2017/3/29 0029.
 */
class Entrance3 implements Runnable {
	private static Count count = new Count();
	private static List<Entrance3> entrances = new ArrayList<Entrance3>();
	private static volatile boolean canceled;
	private final CountDownLatch latch;
	private final int id;
	private int number;

	public Entrance3(CountDownLatch ltc, int id) {
		latch = ltc;
		this.id = id;
		entrances.add(this);
	}

	//	Thinking in Java, 4 th Edition Annotated Solution Guide  656
	public static void cancel() {
		canceled = true;
	}

	public static int getTotalCount() {
		return count.value();
	}

	public static int sumEntrances() {
		int sum = 0;
		for ( Entrance3 entrance : entrances )
			sum += entrance.getValue();
		return sum;
	}

	public void run() {
		while ( !canceled ) {
			synchronized ( this ) {
				++number;
			}
			print(this + " Total: " + count.increment());
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch ( InterruptedException e ) {
				print("sleep interrupted");
			}
		}
		latch.countDown();
		print("Stopping " + this);
	}

	public String toString() {
		return "Entrance " + id + ": " + getValue();
	}

	public synchronized int getValue() {
		return number;
	}
}

public class E32_OrnamentalGarden3 {
	public static void main(String[] args) throws Exception {
// All must share a single CountDownLatch object:
		CountDownLatch latch = new CountDownLatch(5);
		ExecutorService exec = Executors.newCachedThreadPool();
		for ( int i = 0; i < 5; i++ )
			exec.execute(new Entrance3(latch, i));
		TimeUnit.SECONDS.sleep(3);
		Entrance3.cancel();
		exec.shutdown();
		latch.await(); // Wait for results
		print("Total: " + Entrance3.getTotalCount());
		print("Sum of Entrances: " + Entrance3.sumEntrances());
//		Concurrency  657
	}
}