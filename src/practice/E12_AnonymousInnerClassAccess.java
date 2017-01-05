package practice;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class E12_AnonymousInnerClassAccess {
    private int i = 10;

    public static void main(String[] args){
        E12_AnonymousInnerClassAccess ica = new E12_AnonymousInnerClassAccess();
        ica.h();
    }

    private void f( ){
        System.out.println("E12_AnonymousInnerClassAccess.f");
    }

    public void h( ){
        new Object() {
            void g( ){
                i++;
                f();
            }
        }.g();
        System.out.println("i = " + i);
    }
}
