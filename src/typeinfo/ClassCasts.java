package typeinfo;//: typeinfo/ClassCasts.java

class Building1 {}
class House1 extends Building1 {}

public class ClassCasts {
  public static void main(String[] args) {
    Building1 b = new House1();
    Class<House1> houseType = House1.class;
    House1 h = houseType.cast(b);
    h = (House1)b; // ... or just do this.
  }
} ///:~
