package practice;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/24 0024.
 */
class UnlimitedSequence {
    private final List<Object> items = new ArrayList<Object>();

    public void add(Object x){
        items.add(x);
    }

    public Selector selector( ){
        return new SequenceSelector();
    }

    private class SequenceSelector implements Selector {
        private int i;

        @Override
        public boolean end( ){
            return i == items.size();
        }

        @Override
        public Object current( ){
            return items.get(i);
        }

        @Override
        public void next( ){
            if ( i < items.size() )
                i++;
        }

    }
}

public class E03_UnlimitedSequence {

    public static void main(String[] args){
        UnlimitedSequence sequence = new UnlimitedSequence();
        for ( int i = 0; i < 10; i++ ) {
            sequence.add(Integer.toString(i));
        }
        Selector selector = sequence.selector();
        while ( !selector.end() ) {
            System.out.println(selector.current() + " ");
            selector.next();
        }
    }
}
