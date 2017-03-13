package containers;

import java.util.*;

/**
 * Created by Administrator on 2017/3/1 0001.
 */

class CountingMapData extends AbstractMap<Integer, String> {
    private static String[] chars =
            "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z"
                    .split(" ");
    private int size;
    private Set<Map.Entry<Integer, String>> entries = new EntrySet();

    public CountingMapData(int size){
        if ( size < 0 ) this.size = 0;
        this.size = size;
    }

    public Set<Map.Entry<Integer, String>> entrySet( ){
        return entries;
    }

    private class Entry implements Map.Entry<Integer, String> {
        int index;

        Entry(int index){
            this.index = index;
        }

        public boolean equals(Object o){
            return o instanceof Entry &&
                    index == ( ( Entry ) o ).index;
        }

        public Integer getKey( ){
            return index;
        }

        public String getValue( ){
            return
                    chars[index % chars.length] +
                            Integer.toString(index / chars.length);
        }

        public String setValue(String value){
            throw new UnsupportedOperationException();
        }

        public int hashCode( ){
            return Integer.valueOf(index).hashCode();
        }
    }

    class EntrySet extends AbstractSet<Map.Entry<Integer, String>> {
        public int size( ){
            return size;
        }

        public Iterator<Map.Entry<Integer, String>> iterator( ){
            return new Iter();
        }

        private class Iter implements Iterator<Map.Entry<Integer, String>> {
            private Entry entry = new Entry(-1);

            public boolean hasNext( ){
                return entry.index < size - 1;
            }

            public Map.Entry<Integer, String> next( ){
                entry.index++;
                return entry;
            }

            public void remove( ){
                throw new UnsupportedOperationException();
            }
        }
    }
}

public class E05_CountingMapData2 {
    public static void main(String[] args){
        System.out.println(new CountingMapData(60));
    }
}
