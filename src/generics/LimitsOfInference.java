package generics;//: generics/LimitsOfInference.java

import net.mindview.util.New;
import typeinfo.pets.Person;
import typeinfo.pets.Pet;

import java.util.List;
import java.util.Map;

public class LimitsOfInference {
    static void f(Map<Person, List<? extends Pet>> petPeople){
        System.out.println(petPeople.toString());
    }

    public static void main(String[] args){
//        Map map = New.map();
//        map.put("sss","sss");

        f(New.map()); // Does not compile
    }
} ///:~
