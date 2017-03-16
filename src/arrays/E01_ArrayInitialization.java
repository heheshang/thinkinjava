package arrays;

/**
 * Created by Administrator on 2017/2/28 0028.
 */
public class E01_ArrayInitialization {
    static void hide(BerylliumSphere[] s){
        System.out.println("Hiding " + s.length + " sphere(s)");
    }

    public static void main(String[] args){
// Dynamic aggregate initialization:
        hide(new BerylliumSphere[]{new BerylliumSphere(),
                new BerylliumSphere()});
// The following line produces a compilation error.
//! hide({ new BerylliumSphere() });
// Aggregate initialization:
        BerylliumSphere[] d = {new BerylliumSphere(),
                new BerylliumSphere(), new BerylliumSphere()};
        hide(d);
// Dynamic aggregate initialization is redundant
// in the next case:
        BerylliumSphere[] a = new BerylliumSphere[]{
                new BerylliumSphere(), new BerylliumSphere()};
        hide(a);
    }
}
