package annotations;

import net.mindview.atunit.Test;
import net.mindview.util.OSExecute;

import java.util.LinkedList;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
public class E06_LinkedListTest {
    LinkedList<String> testObject = new LinkedList<String>();

    public static void main(String[] args) throws Exception{
        OSExecute.command("java " +
                " net.mindview.atunit.AtUnit E06_LinkedListTest");
    }

    @Test
    void initialization( ){
        assert testObject.isEmpty();
    }

    @Test
    void _contains( ){
        testObject.add("one");
        assert testObject.contains("one");
    }

    @Test
    void _remove( ){
        testObject.add("one");
        testObject.remove("one");
        assert testObject.isEmpty();
    }
}
