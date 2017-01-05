package practice;

/**
 * 类Outer.java的功能描述:TODO
 * @author ssk 2016-11-07 下午 5:44 www.hnapay.com Inc.All rights reserved
 * @version v1.0
 */
class Outer {
    class Inner {
        {
            System.out.println("Inner created!");
        }
    }

    Inner getInner() {
        return new Inner();
    }
}

public class E01_ReferenceToInnerClass {
    public static void main(String[] args) {
        Outer o = new Outer();
        Outer.Inner i = o.getInner();
    }
}
