package annotations;

import net.mindview.atunit.Test;
import net.mindview.util.OSExecute;

import java.util.LinkedList;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
public class E07_LinkedListTest2 extends LinkedList<String> {
    public static void main(String[] args) throws Exception{
        OSExecute.command("java " +
                " net.mindview.atunit.AtUnit E07_LinkedListTest2");
    }

    @Test
    void initialization( ){
        assert isEmpty();
    }

    @Test
    void _contains( ){
        add("one");
        assert contains("one");
    }

    @Test
    void _remove( ){
//        Annotations  587
        add("one");
        remove("one");
        assert isEmpty();
    }
}
