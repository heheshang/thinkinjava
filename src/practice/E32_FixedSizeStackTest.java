package practice;

/**
 * Created by Administrator on 2017/2/8 0008.
 */
public class E32_FixedSizeStackTest {
    public static void main(String[] args){
        FixedSizeStack<Integer> stack = new FixedSizeStack<Integer>(1);
        stack.push(1);
        System.out.println(stack.pop());
        try {
            stack.pop();
        } catch ( ArrayIndexOutOfBoundsException e ) {
            System.out.println(e.toString());
        }
        stack = new FixedSizeStack<Integer>(1);
        stack.push(2);
        try {
            stack.push(2);
        } catch ( ArrayIndexOutOfBoundsException e ) {
            System.out.println(e.toString());
        }
    }

    private static class FixedSizeStack<T> {
        private int index = 0;
        private Object[] storage;

        public FixedSizeStack(int size){
            storage = new Object[size];
        }

        public void push(T item){
            storage[index++] = item;
        }

        @SuppressWarnings("unchecked")
        public T pop( ){
            return ( T ) storage[--index];
        }
    }
}
