package practice;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class E09_InnerClassInMethod {
    public SimpleInterface get() {
        class SI implements SimpleInterface{
            public void f() { System.out.println("SI.f"); }
        }
        return new SI();
    }

    public static void main(String[] args){
        SimpleInterface si = new E09_InnerClassInMethod().get();
        si.f();
    }
}
