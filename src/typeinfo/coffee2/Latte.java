package typeinfo.coffee2;

/**
 * Created by Administrator on 2016/12/29 0029.
 */
public class Latte extends Coffee {
    public static class Factory
            implements typeinfo.factory.Factory<Latte> {
        public Latte create( ){
            return new Latte();
        }
    }
}
