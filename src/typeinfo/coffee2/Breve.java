package typeinfo.coffee2;

/**
 * Created by Administrator on 2016/12/29 0029.
 */
public class Breve extends Coffee {
    public static class Factory
            implements typeinfo.factory.Factory<Breve> {
        public Breve create( ){
            return new Breve();
        }
    }
}
