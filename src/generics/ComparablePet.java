package generics;//: generics/ComparablePet.java

public class ComparablePet
implements Comparable<ComparablePet> {
  public int compareTo(ComparablePet arg) { return 0; }
} ///:~

//class Cat extends ComparablePet implements Comparable<Cat>{
//  public int compareTo(ComparablePet arg) { return 0; }
//
//}
//class Hamster extends ComparablePet implements Comparable<ComparablePet>{
//  public int compareTo(ComparablePet arg) { return 0; }
//}