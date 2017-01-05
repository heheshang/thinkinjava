package practice;

import typeinfo.pets.Pet;

import java.util.*;

/**
 * Created by Administrator on 2016/12/1 0001.
 */

class NonCollectionSequence extends PetSequence implements Iterable<Pet> {
    public Iterable<Pet> reversed( ){
        return new Iterable<Pet>() {
            public Iterator<Pet> iterator( ){
                return new Iterator<Pet>() {
                    int current = pets.length - 1;

                    public boolean hasNext( ){
                        return current > -1;
                    }

                    public Pet next( ){
                        return pets[current--];
                    }

                    public void remove( ){ // Not implemented
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    public Iterable<Pet> randomized( ){
        return new Iterable<Pet>() {
            public Iterator<Pet> iterator( ){
                List<Pet> shuffled = new ArrayList<Pet>(Arrays.asList(pets));
                Collections.shuffle(shuffled, new Random(47));
                return shuffled.iterator();
            }
        };
    }

    public Iterator<Pet> iterator( ){
        return new Iterator<Pet>() {
            private int index = 0;

            public boolean hasNext( ){
                return index < pets.length;
            }

            public Pet next( ){
                return pets[index++];
            }

            public void remove( ){ // Not implemented
                throw new UnsupportedOperationException();
            }
        };
    }
}

public class E32_MultiIterableNonCollectionSeq {
    public static void main(String[] args){
        NonCollectionSequence nc = new NonCollectionSequence();
        for ( Pet pet : nc.reversed() )
            System.out.print(pet + " ");
        System.out.println();
        for ( Pet pet : nc.randomized() )
            System.out.print(pet + " ");
        System.out.println();
        for ( Pet pet : nc )
            System.out.print(pet + " ");
    }
}
