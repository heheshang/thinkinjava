package typeinfo;

import net.mindview.util.TypeCounter;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * Created by Administrator on 2016/12/28 0028.
 */
public class E13_PartCount {
    public static void main(String[] args){
        TypeCounter counter = new TypeCounter(Part.class);
        Part part;
        for ( int i = 0; i < 20; i++ ) {
            part = Part.createRandom();
            printnb(part.getClass().getSimpleName() + " ");
            counter.count(part);
        }
        print();
        print(counter);
    }
}
