package practice;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class E10_InnerClassInScope {
    public static void main(String[] args){
        SimpleInterface si = new E10_InnerClassInScope().get();
        si.f();
    }

    public SimpleInterface get( ){
        {
            class SI implements SimpleInterface {
                @Override
                public void f( ){
                    System.out.println("SI.f");
                }
            }
            return new SI();
        }
    }
}
