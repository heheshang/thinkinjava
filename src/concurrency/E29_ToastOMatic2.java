package concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Created by Administrator on 2017/3/29 0029.
 */
class Toast1 {
	private final int id;
	private Status status = Status.DRY;

	public Toast1(int idn) {
		id = idn;
	}

	public void butter() {
		status =
				( status == Status.DRY ) ? Status.BUTTERED :
						Status.READY;
	}

	public void jam() {
		status =
				( status == Status.DRY ) ? Status.JAMMED :
						Status.READY;
	}

	public Status getStatus() {
		return status;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return "Toast " + id + ": " + status;
	}

	public enum Status {
		DRY,
		BUTTERED,
		JAMMED,
		READY {
			public String toString() {
				return
						BUTTERED.toString() + " & " + JAMMED.toString();
			}
		}
	}
}

//	Thinking in Java, 4 th Edition Annotated Solution Guide  646
class ToastQueue1 extends LinkedBlockingQueue<Toast1> {
}

class Toaster1 implements Runnable {
	private ToastQueue1 toastQueue;
	private int count;
	private Random rand = new Random(47);

	public Toaster1(ToastQueue1 tq) {
		toastQueue = tq;
	}

	public void run() {
		try {
			while ( !Thread.interrupted() ) {
				TimeUnit.MILLISECONDS.sleep(
						100 + rand.nextInt(500));
// Make toast
				Toast1 t = new Toast1(count++);
				print(t);
// Insert into queue
				toastQueue.put(t);
			}
		} catch ( InterruptedException e ) {
			print("Toaster interrupted");
		}
		print("Toaster off");
	}
}

// Apply butter to toast:
class Butterer1 implements Runnable {
	private ToastQueue1 inQueue, butteredQueue;

	public Butterer1(ToastQueue1 in, ToastQueue1 buttered) {
		inQueue = in;
		butteredQueue = buttered;
	}

	public void run() {
		try {
			while ( !Thread.interrupted() ) {
// Blocks until next piece of toast is available:
				Toast1 t = inQueue.take();
				t.butter();
				print(t);
				butteredQueue.put(t);
			}
		} catch ( InterruptedException e ) {
			print("Butterer interrupted");
		}
		print("Butterer off");
	}
}

//Concurrency  647
// Apply jam to toast:
class Jammer1 implements Runnable {
	private ToastQueue1 inQueue, jammedQueue;

	public Jammer1(ToastQueue1 in, ToastQueue1 jammed) {
		inQueue = in;
		jammedQueue = jammed;
	}

	public void run() {
		try {
			while ( !Thread.interrupted() ) {
// Blocks until next piece of toast is available:
				Toast1 t = inQueue.take();
				t.jam();
				print(t);
				jammedQueue.put(t);
			}
		} catch ( InterruptedException e ) {
			print("Jammer interrupted");
		}
		print("Jammer off");
	}
}

// Consume the toast:
class Eater1 implements Runnable {
	private ToastQueue1 finishedQueue;

	public Eater1(ToastQueue1 finished) {
		finishedQueue = finished;
	}

	public void run() {
		try {
			while ( !Thread.interrupted() ) {
// Blocks until next piece of toast is available:
				Toast1 t = finishedQueue.take();
// Verify that all pieces are ready for consumption:
				if ( t.getStatus() != Toast1.Status.READY ) {
					print(">>>> Error: " + t);
					System.exit(1);
				} else
					print("Chomp! " + t);
			}
		} catch ( InterruptedException e ) {
			print("Eater interrupted");
		}
		print("Eater off");
	}
//	Thinking in Java, 4 th Edition Annotated Solution Guide  648
}

// Outputs alternate inputs on alternate channels:
class Alternator implements Runnable {
	private ToastQueue1 inQueue, out1Queue, out2Queue;
	private boolean outTo2; // control alternation

	public Alternator(ToastQueue1 in, ToastQueue1 out1,
					  ToastQueue1 out2) {
		inQueue = in;
		out1Queue = out1;
		out2Queue = out2;
	}

	public void run() {
		try {
			while ( !Thread.interrupted() ) {
// Blocks until next piece of toast is available:
				Toast1 t = inQueue.take();
				if ( !outTo2 )
					out1Queue.put(t);
				else
					out2Queue.put(t);
				outTo2 = !outTo2; // change state for next time
			}
		} catch ( InterruptedException e ) {
			print("Alternator interrupted");
		}
		print("Alternator off");
	}
}

// Accepts toasts on either channel, and relays them on to
// a "single" successor
class Merger implements Runnable {
	private ToastQueue1 in1Queue, in2Queue, toBeButteredQueue,
			toBeJammedQueue, finishedQueue;

	public Merger(ToastQueue1 in1, ToastQueue1 in2,
				  ToastQueue1 toBeButtered, ToastQueue1 toBeJammed,
				  ToastQueue1 finished) {
		in1Queue = in1;
		in2Queue = in2;
		toBeButteredQueue = toBeButtered;
		toBeJammedQueue = toBeJammed;
		finishedQueue = finished;
	}

	public void run() {
		try {
			while ( !Thread.interrupted() ) {
//				Concurrency  649
// Blocks until next piece of toast is available:
				Toast1 t = null;
				while ( t == null ) {
					t = in1Queue.poll(50, TimeUnit.MILLISECONDS);
					if ( t != null )
						break;
					t = in2Queue.poll(50, TimeUnit.MILLISECONDS);
				}
// Relay toast onto the proper queue
				switch ( t.getStatus() ) {
					case BUTTERED:
						toBeJammedQueue.put(t);
						break;
					case JAMMED:
						toBeButteredQueue.put(t);
						break;
					default:
						finishedQueue.put(t);
				}
			}
		} catch ( InterruptedException e ) {
			print("Merger interrupted");
		}
		print("Merger off");
	}
}

public class E29_ToastOMatic2 {
	public static void main(String[] args) throws Exception {
		ToastQueue1
				dryQueue = new ToastQueue1(),
				butteredQueue = new ToastQueue1(),
				toBeButteredQueue = new ToastQueue1(),
				jammedQueue = new ToastQueue1(),
				toBeJammedQueue = new ToastQueue1(),
				finishedQueue = new ToastQueue1();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Toaster1(dryQueue));
		exec.execute(new Alternator(dryQueue, toBeButteredQueue,
				toBeJammedQueue));
		exec.execute(
				new Butterer1(toBeButteredQueue, butteredQueue));
		exec.execute(
				new Jammer1(toBeJammedQueue, jammedQueue));
		exec.execute(new Merger(butteredQueue, jammedQueue,
				toBeButteredQueue, toBeJammedQueue, finishedQueue));
		exec.execute(new Eater1(finishedQueue));
//		Thinking in Java, 4 th Edition Annotated Solution Guide  650
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}