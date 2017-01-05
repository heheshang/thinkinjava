package practice;

/**
 * Created by Administrator on 2016/11/7 0007.
 */
public class ImplementInterface implements AnInterface {
    @Override
    public void f() {
        System.out.println("ImplementInterface.f");
    }

    @Override
    public void g() {
        System.out.println("ImplementInterface.g");
    }

    @Override
    public void h() {
        System.out.println("ImplementInterface.h");
    }
}
