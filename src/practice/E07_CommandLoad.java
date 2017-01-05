package practice;

import static net.mindview.util.Print.print;

/**
 * Created by Administrator on 2016/12/28 0028.
 */

class Candy {
    static{
        print("Loading Candy");
    }
}

class Gum {
    static{
        print("Loading Gum");
    }
}

class Cookie {
    static{
        print("Loading Cookie");
    }
}

public class E07_CommandLoad {
    public static void main(String[] args) throws Exception{
        for ( String arg : args ) {
            Class.forName(arg);
        }
    }
}
