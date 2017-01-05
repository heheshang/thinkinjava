package typeinfo.coffee2;

/**
 * Created by Administrator on 2016/12/29 0029.
 */
public class Americano extends Coffee {
    public static class Factory
            implements typeinfo.factory.Factory<Americano> {
        public Americano create( ){
            return new Americano();
        }
    }
}
