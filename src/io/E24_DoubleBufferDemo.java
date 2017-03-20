package io;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;

/**
 * Created by Administrator on 2017/3/17 0017.
 */
public class E24_DoubleBufferDemo {
    private static final int BSIZE = 1024;

    public static void main(String[] args){
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        DoubleBuffer db = bb.asDoubleBuffer();
// Store an array of double:
        db.put(
                new double[]{1.0, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6});
// Absolute location read and write:
        System.out.println(db.get(3));
        db.put(3, 0.3);
        db.flip();
        while ( db.hasRemaining() ) {
            double d = db.get();
            System.out.println(d);
        }
    }
}