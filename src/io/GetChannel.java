package io;//: io/GetChannel.java
// Getting channels from streams

import java.nio.*;
import java.nio.channels.*;
import java.io.*;

public class GetChannel {
    private static final int BSIZE = 1024;

    public static void main(String[] args) throws Exception{
        // Write a file:
        FileChannel fc = new FileOutputStream("F:\\think\\thinkinjava\\src\\io\\Data.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text ".getBytes()));
        fc.close();
        // Add to the end of the file:
        fc = new RandomAccessFile("F:\\think\\thinkinjava\\src\\io\\Data.txt", "rw").getChannel();
        fc.position(fc.size()); // Move to the end
        fc.write(ByteBuffer.wrap("Some moresdsddsd".getBytes()));
        fc.close();
        // Read the file:
        fc = new FileInputStream("F:\\think\\thinkinjava\\src\\io\\Data.txt").getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        fc.read(buff);
        buff.flip();
        while ( buff.hasRemaining() )
            System.out.print(( char ) buff.get());
    }
//    Channels
} /* Output:
Some text Some more
*///:~
