package typeinfo.coffee2;

/**
 * Created by Administrator on 2016/12/29 0029.
 */
public class Mocha extends Coffee {
    public static class Factory
            implements typeinfo.factory.Factory<Mocha> {
        public Mocha create( ){
            return new Mocha();
        }
    }
}
