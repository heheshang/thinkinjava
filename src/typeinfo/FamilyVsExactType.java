//: typeinfo/FamilyVsExactType.java
// The difference between instanceof and class
package typeinfo;

import static net.mindview.util.Print.*;

class Base {
}

class Derived extends Base {
}

public class FamilyVsExactType {
    static void test(Object x){
        print("Testing x of type " + x.getClass());
        print("x instanceof Base " + ( x instanceof Base ));
        print("x instanceof Derived " + ( x instanceof Derived ));
        print("Base.isInstance(x) " + Base.class.isInstance(x));
        print("Derived.isInstance(x) " +
                Derived.class.isInstance(x));
        print("x.getClass() == Base.class " +
                ( x.getClass() == Base.class ));
        print("x.getClass() == Derived.class " +
                ( x.getClass() == Derived.class ));
        print("x.getClass().equals(Base.class)) " +
                ( x.getClass().equals(Base.class) ));
        print("x.getClass().equals(Derived.class)) " +
                ( x.getClass().equals(Derived.class) ));
        /**
         * 本处不能用switch
         * 1.switch-case注意事项：

         switch(A),括号中A的取值只能是整型或者可以转换为整型的数值类型，比如byte、short、int、char、还有枚举；需要强调的是：long和String类型是不能作用在switch语句上的。

         case B：C；case是常量表达式，也就是说B的取值只能是常量（需要定义一个final型的常量,后面会详细介绍原因）或者int、byte、short、char（比如1、2、3、200000000000（注意了这是整型）），如果你需要在此处写一个表达式或者变量，那么就要加上单引号； case后的语句可以不用大括号，就是C不需要用大括号包裹着；

         default就是如果没有符合的case就执行它,default并不是必须的.
         */
    }

    public static void main(String[] args){
        test(new Base());
        test(new Derived());
    }
} /* Output:
Testing x of type class typeinfo.Base
x instanceof Base true
x instanceof Derived false
Base.isInstance(x) true
Derived.isInstance(x) false
x.getClass() == Base.class true
x.getClass() == Derived.class false
x.getClass().equals(Base.class)) true
x.getClass().equals(Derived.class)) false
Testing x of type class typeinfo.Derived
x instanceof Base true
x instanceof Derived true
Base.isInstance(x) true
Derived.isInstance(x) true
x.getClass() == Base.class false
x.getClass() == Derived.class true
x.getClass().equals(Base.class)) false
x.getClass().equals(Derived.class)) true
*///:~
