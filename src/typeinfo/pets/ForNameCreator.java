//: typeinfo/pets/ForNameCreator.java
package typeinfo.pets;

import java.util.ArrayList;
import java.util.List;

public class ForNameCreator extends PetCreator {
    private static List<Class<? extends Pet>> types =
            new ArrayList<Class<? extends Pet>>();
    // Types that you want to be randomly created:
    private static String[] typeNames = {
            "typeinfo.pets.Mutt",
            "typeinfo.pets.Pug",
            "typeinfo.pets.EgyptianMau",
            "typeinfo.pets.Manx",
            "typeinfo.pets.Cymric",
            "typeinfo.pets.Rat",
            "typeinfo.pets.Mouse",
            "typeinfo.pets.Hamster"
    };

    static{
        loader();
    }

    @SuppressWarnings("unchecked")
    private static void loader( ){
        try {
            for ( String name : typeNames )
                types.add(( Class<? extends Pet> ) Class.forName(name));
        } catch ( ClassNotFoundException e ) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        loader();
        for ( Class<? extends Pet> type : types ) {

            try {
                if ( type.newInstance() instanceof Mutt) {
                    System.out.println(type.newInstance());
                    System.out.println(type.getName());
                    System.out.println("Mutt");
                }
            } catch ( InstantiationException e ) {
                e.printStackTrace();
            } catch ( IllegalAccessException e ) {
                e.printStackTrace();
            }

        }
    }

    public List<Class<? extends Pet>> types( ){
        return types;
    }
} ///:~
