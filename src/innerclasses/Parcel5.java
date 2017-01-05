package innerclasses;//: innerclasses/Parcel5.java
// Nesting a class within a method.

public class Parcel5 {
    public static void main(String[] args){
        Parcel5 p = new Parcel5();
        Destination d = p.destination("Tasmania");
    }

    public Destination destination(String s){
        class PDestination implements Destination {
            private String label;

            private PDestination(String whereTo){

                System.out.println("PDestination " + whereTo);
                label = whereTo;
            }

            public String readLabel( ){
                return label;
            }
        }
        PDestination pDestination = new PDestination(s);
        System.out.println("pDestination "+pDestination.label);
        return pDestination;
    }
} ///:~
