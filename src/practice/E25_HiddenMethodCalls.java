package practice;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/12/30 0030.
 */
public class E25_HiddenMethodCalls {
    static void callHiddenMethod(Object a, String methodName)
            throws Exception{
        Method g = a.getClass().getDeclaredMethod(methodName);
        g.setAccessible(true);
        g.invoke(a);
    }

    public static void main(String[] args) throws Exception{
        class B extends Assss {
            protected void b( ){
                super.b();
            }
        }
        Assss objA = new Assss();
        //! objA.a(); Compile time error
        //! objA.b(); Compile time error
        //! objA.c(); Compile time error
        callHiddenMethod(objA, "a");
        callHiddenMethod(objA, "b");
        callHiddenMethod(objA, "c");
        B objB = new B();
        objB.b();
    }
}

