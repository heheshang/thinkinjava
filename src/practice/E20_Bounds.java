package practice;

/**
 * Created by Administrator on 2017/1/5 0005.
 */
interface Top{
    void a();
    void b();
}
class CombinedImpl implements Top{

    @Override
    public void a( ){
        System.out.println("Top::a()");
    }

    @Override
    public void b( ){
        System.out.println("Top::b()");
    }
    public void c(){
        System.out.println("CombinedImpl::c()");
    }
}
public class E20_Bounds {
    static <T extends Top> void f(T object){
        object.a();
        object.b();

    }

    public static void main(String[] args){
        f(new CombinedImpl());
    }
}
