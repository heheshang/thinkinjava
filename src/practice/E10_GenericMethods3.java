package practice;

/**
 * Created by Administrator on 2017/1/3 0003.
 */
public class E10_GenericMethods3 {
    public <A,B> void f(A a, B b, Boolean c) {
        System.out.println(a.getClass().getName());
        System.out.println(b.getClass().getName());
        System.out.println(c.getClass().getName());
    }
    public static void main(String[] args) {
        E10_GenericMethods3 gm = new E10_GenericMethods3();
        gm.f("", 1, true);
        gm.f(1.0, 1.0F, false);
        gm.f('c', gm, true);
    }
}
