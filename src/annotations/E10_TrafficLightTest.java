package annotations;

import net.mindview.atunit.Test;
import net.mindview.atunit.TestProperty;
import net.mindview.util.OSExecute;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
enum Signal {
    GREEN, YELLOW, RED,
}

class TrafficLight {
    Signal color = Signal.RED;

    public void change( ){
        switch ( color ) {
// Note that you don't have to say Signal.RED
// in the case statement:
            case RED:
                color = Signal.GREEN;
                break;
            case GREEN:
                color = Signal.YELLOW;
                break;
            case YELLOW:
                color = Signal.RED;
                break;
        }
    }

    public String toString( ){
        return "The traffic light is " + color;
    }
}

//    Thinking in Java, 4 th Edition Annotated Solution Guide  590
public class E10_TrafficLightTest {
    TrafficLight testObject = new TrafficLight();

    @TestProperty
    private static String str(Signal s){
        return "The traffic light is " + s;
    }

    public static void main(String[] args) throws Exception{
        OSExecute.command("java " + " net.mindview.atunit.AtUnit E10_TrafficLightTest");
    }

    @Test
    void initialization( ){
        assert testObject.toString().equals(str(Signal.RED));
    }

    @Test
    void _change( ){
        testObject.change();
        assert testObject.toString().equals(str(Signal.GREEN));
        testObject.change();
        assert testObject.toString().equals(str(Signal.YELLOW));
        testObject.change();
        assert testObject.toString().equals(str(Signal.RED));
    }
} /* Output:
annotations.E10_TrafficLightTest
. initialization
. _change
OK (2 tests)
*///:~