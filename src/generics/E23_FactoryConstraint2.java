package generics;

/**
 * Created by Administrator on 2017/1/6 0006.
 */
interface FactoryL<T>{
    T create(int arg);
}

class Foo<T>{
    private T x;
    public Foo(FactoryL<T> facctoryL){
        x =facctoryL.create(1);
    }
}
class IntegerFactoryL implements FactoryL<Integer>{

    @Override
    public Integer create(int arg){
        return new Integer(arg);
    }
}
class Widght{
    private final int id;

    public Widght(int id){
        this.id = id;
    }

    @Override
    public String toString( ){
        return "Widght "+ id;
    }
    public static class Factory implements FactoryL<Widght> {
        public Widght create(int arg) {
            return new Widght(arg);
        }
    }
}
public class E23_FactoryConstraint2 {
    public static void main(String[] args){
        new Foo<Integer>(new IntegerFactoryL());

    }
}
