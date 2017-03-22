package concurrency;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
public class E08_MoreBasicDaemonThreads {
    public static void main(String[] args) {
        for(int i = 0; i < 5; i++) {
            Thread t = new Thread(new LiftOff());
            t.setDaemon(true);
            t.start();
        }
        System.out.println("Waiting for LiftOff");
    }
}
