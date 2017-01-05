package practice;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public class E19_InnerInsideInner {
    public static void main(String[] args){
        new E19_InnerInsideInner.Nested1().f();
        new E19_InnerInsideInner.Nested1.Nested2().f();
        E19_InnerInsideInner x1 = new E19_InnerInsideInner();
        E19_InnerInsideInner.Inner1 x2 = x1.makeInner1();
        E19_InnerInsideInner.Inner1.Inner2 x3 = x2.makeInner2();
        x3.f();
    }

    Inner1 makeInner1( ){
        return new Inner1();
    }

    static class Nested1 {
        void f( ){
        }

        static class Nested2 {
            void f( ){
            }
        }
    }

    class Inner1 {
        Inner2 makeInner2( ){
            return new Inner2();
        }

        class Inner2 {
            void f( ){
            }
        }
    }
}
/**
 *
 * E19_InnerInsideInner.class
 * E19_InnerInsideInner$Inner1.class
 * E19_InnerInsideInner$Inner1$Inner2.class
 * E19_InnerInsideInner$Nested1.class
 * E19_InnerInsideInner$Nested1$Nested2.class
 *
 */