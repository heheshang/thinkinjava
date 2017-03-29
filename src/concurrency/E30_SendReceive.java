package concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * Created by Administrator on 2017/3/29 0029.
 */
class CharQueue extends LinkedBlockingQueue<Character> {
}

class Sender1 implements Runnable {
	private Random rand = new Random(47);
	private CharQueue out = new CharQueue();

	public CharQueue getQueue() {
		return out;
	}

	public void run() {
		try {
			while ( true )
				for ( char c = 'A'; c <= 'z'; c++ ) {
					out.put(c);
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
//					Concurrency  651
				}
		} catch ( InterruptedException e ) {
			print(e + " Sender interrupted");
		}
	}
}

class Receiver1 implements Runnable {
	private CharQueue in;

	public Receiver1(Sender1 sender) {
		in = sender.getQueue();
	}

	public void run() {
		try {
			while ( true ) {
// Blocks until characters are there:
				printnb("Read: " + in.take() + ", ");
			}
		} catch ( InterruptedException e ) {
			print(e + " Reader interrupted");
		}
	}
}

public class E30_SendReceive {
	public static void main(String[] args) throws Exception {
		Sender sender = new Sender();
		Receiver receiver = new Receiver(sender);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(sender);
		exec.execute(receiver);
		TimeUnit.SECONDS.sleep(4);
		exec.shutdownNow();
	}
}