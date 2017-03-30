package io;

import java.io.*;

import static net.mindview.util.Print.print;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class E28_BlipCheck implements Externalizable {
     E28_BlipCheck() {
 print("BlipCheck Constructor");
     }
    public void writeExternal(ObjectOutput out)
            throws IOException {
        print("BlipCheck.writeExternal");
    }
    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {
        print("BlipCheck.readExternal");
    }
    public static void main(String[] args) throws Exception {
// To make it run with Ant.
        Blips.main(args);
    }
}

