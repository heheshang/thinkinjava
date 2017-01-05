package practice;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public interface WithNested {
    class Nested {
        int i;

        public Nested(int i){
            this.i = i;
        }

        void f( ){
            System.out.println("Nested.f");
        }
    }

    class B2 implements WithNested {

    }

    public class E20_InterfaceWithNested {
        public static void main(String[] args){
            B2 b = new B2();
            WithNested.Nested ne = new WithNested.Nested(5);
            ne.f();
        }
    }
}
