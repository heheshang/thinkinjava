package concurrency;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Created by Administrator on 2017/3/30 0030.
 */
public class E41_ActiveObjectDemo2 {
	private ExecutorService ex = Executors.newSingleThreadExecutor();
	private Random rand = new Random(47);

	public static void main(String[] args) {
		E41_ActiveObjectDemo2 d1 = new E41_ActiveObjectDemo2();
		// Prevents ConcurrentModificationException:
		List<Future<?>> results = new CopyOnWriteArrayList<Future<?>>();
		for ( float f = 0.0f; f < 1.0f; f += 0.2f )
			results.add(d1.calculateFloat(f, f));
		for ( int i = 0; i < 5; i++ ) {
			results.add(d1.calculateInt(i, i));
			d1.printDocument("DOC_" + i);
		}
		print("All asynch calls made");
		while ( results.size() > 0 ) {
			for ( Future<?> f : results )
				if ( f.isDone() ) {
					try {
						print(f.get());
					} catch ( Exception e ) {
						throw new RuntimeException(e);
					}
					results.remove(f);
				}
		}
		//		Thinking in Java, 4 th Edition Annotated Solution Guide  694
		d1.shutdown();
	}

	public Future<Float> calculateFloat(final float x, final float y) {
		return ex.submit(new Callable<Float>() {
			public Float call() {
				print("starting " + x + " + " + y);
				pause(2000);
				return x + y;
			}
		});
	}

	public Future<Integer> calculateInt(final int x, final int y) {
		return ex.submit(new Callable<Integer>() {
			public Integer call() {
				print("starting " + x + " + " + y);
				pause(500);
				return x + y;
			//				Concurrency  693
			}
		});
	}

	// Message handler without a return value:
	public void printDocument(final String s) {
		ex.execute(new Runnable() {
			public void run() {
				print("printing document " + s);
				pause(1000);
				print("document " + s + " printed");
			}
		});
	}

	public void shutdown() {
		ex.shutdown();
	}

	// Insert a random delay to produce the effect
	// of a calculation time:
	private void pause(int factor) {
		try {
			TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(factor));
		} catch ( InterruptedException e ) {
			print("sleep() interrupted");
		}
	}
}