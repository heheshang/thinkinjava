package concurrency;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

import static net.mindview.util.Print.print;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
class LiftOffRunnerTest implements Runnable {
	private BlockingQueue<LiftOff> rockets;

	public LiftOffRunnerTest(BlockingQueue<LiftOff> queue) {
		rockets = queue;
	}

	public void add(LiftOff lo) throws InterruptedException {
		rockets.put(lo);
	}

	public void run() {
		try {
			while ( !Thread.interrupted() ) {
				LiftOff rocket = rockets.take();
				rocket.run(); // Use this thread
			}
		} catch ( InterruptedException e ) {
			print("Waking from take()");
		}
		print("Exiting LiftOffRunner");
	}
}

class LiftOffProducerTest implements Runnable {
	private LiftOffRunnerTest runner;

	public LiftOffProducerTest(LiftOffRunnerTest runner) {
		this.runner = runner;
	}

	public void run() {
		try {
//            Thinking in Java, 4 th Edition Annotated Solution Guide  644
			for ( int i = 0; i < 5; i++ )
				runner.add(new LiftOff(5));
		} catch ( InterruptedException e ) {
			print("Waking from put()");
		}
		print("Exiting LiftOffProducer");
	}
}

public class E28_TestBlockingQueues2 {
	public static void main(String[] args) {
		test("LinkedBlockingQueue", new LinkedBlockingQueue<LiftOff>()); // Unlimited size
		test("ArrayBlockingQueue", new ArrayBlockingQueue<LiftOff>(3));// Fixed size
		test("SynchronousQueue", new SynchronousQueue<LiftOff>());// Size of 1
	}

	private static void test(String msg, BlockingQueue<LiftOff> queue) {
		ExecutorService exec = Executors.newFixedThreadPool(2);
		print(msg);
		LiftOffRunnerTest runner = new LiftOffRunnerTest(queue);
		LiftOffProducerTest producer = new LiftOffProducerTest(runner);
		exec.execute(runner);
		exec.execute(producer);
		getkey("Press 'ENTER' (" + msg + ")");
		exec.shutdownNow();
		print("Finished " + msg + " test");
	}

	private static void getkey(String message) {
		print(message);
		getkey();
	}

	private static void getkey() {
		try {
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch ( java.io.IOException e ) {
			throw new RuntimeException(e);
		}
	}
} ///:~