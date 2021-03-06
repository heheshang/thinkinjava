package annotations;

import net.mindview.atunit.Test;
import net.mindview.atunit.TestProperty;
import net.mindview.util.OSExecute;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
class PrivateMethod {
    private boolean hidden() { return true; }
    @TestProperty
    boolean visible() { return hidden(); }
}
public class E08_TestPrivateMethod extends PrivateMethod {
    @Test
    void _hidden() { assert visible(); }
    public static void main(String[] args) {
        OSExecute.command("java " +
                " net.mindview.atunit.AtUnit E08_TestPrivateMethod");
    }
} /* Output:
annotations.E08_TestPrivateMethod
. _hidden
OK (1 tests)
*///:~