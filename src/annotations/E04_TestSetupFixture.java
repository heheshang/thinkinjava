package annotations;

import net.mindview.atunit.Test;
import net.mindview.util.OSExecute;

import java.util.HashSet;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
public class E04_TestSetupFixture {
    HashSet<String> testObject = new HashSet<String>();
    @Test
    void _t1() {
        assert testObject.isEmpty();
        testObject.add("one");
    }
    @Test void _t2() {
        assert testObject.isEmpty();
        testObject.add("one");
    }
    public static void main(String[] args) throws Exception {
        OSExecute.command("java " +
                "net.mindview.atunit.AtUnit E04_TestSetupFixture");
    }
}
/* Output:
annotations.E04_TestSetupFixture
. _t1
. _t2
OK (2 tests)
*///:~
//We create a new testObject before each test; otherwise either _t1 or _t2 would fail (depending on the order of execution).
