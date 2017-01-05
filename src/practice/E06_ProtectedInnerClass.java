package practice;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
public class E06_ProtectedInnerClass extends SimpleClass {
    public SimpleInterface get(){
        return new Inner();
    }

    public static void main(String[] args) {
        new E06_ProtectedInnerClass();
    }
}
