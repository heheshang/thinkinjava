package typeinfo.coffee2;

/**
 * Created by Administrator on 2016/12/29 0029.
 */
public class Cappucino extends Coffee {
    public static class Factory
            implements typeinfo.factory.Factory<Cappucino> {
        public Cappucino create( ){
            return new Cappucino();
        }
    }
}
