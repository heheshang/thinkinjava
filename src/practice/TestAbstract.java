package practice;

/**
 * Created by Administrator on 2016/11/7 0007.
 */
public abstract  class TestAbstract {
    TestAbstract(){
        System.out.println("TestAbstract()");
        print();
    }
    public abstract void print();
}
class B extends TestAbstract{
    private int i = 1;
    @Override
    public void print() {
        System.out.println(i);
    }
}
class A{
    public static void main(String[] args) {
        B b = new B();
        b.print();
    }
}
