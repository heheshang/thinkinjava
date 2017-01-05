package practice;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class E13_AnonymousInnerClassInMethod {
    public static void main(String[] args){
        SimpleInterface si = new E13_AnonymousInnerClassInMethod().get();
        si.f();

    }

    public SimpleInterface get( ){
        return new SimpleInterface() {
            @Override
            public void f( ){
                System.out.println("SimpleInterface.f");
            }
        };
    }
}
