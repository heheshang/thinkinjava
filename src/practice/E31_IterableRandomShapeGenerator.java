package practice;

import polymorphism.shape.Circle;
import polymorphism.shape.Shape;
import polymorphism.shape.Square;
import polymorphism.shape.Triangle;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
class RandomShapeGenerator implements Iterable<Shape> {
    private Random rand = new Random(47);
    private final int quantity;
    RandomShapeGenerator(int quantity) {
        this.quantity = quantity;
    }
    public Iterator<Shape> iterator() {
        return new Iterator<Shape>() {
            private int count;
            public boolean hasNext() {
                return count < quantity;
            }
            public Shape next() {
                ++count;
                return nextShape();
            }
            public void remove() { // Not implemented
                throw new UnsupportedOperationException();
            }
        };
    }
    private Shape nextShape() {
        switch(rand.nextInt(3)) {
            default:
            case 0: return new Circle();
            case 1: return new Square();
            case 2: return new Triangle();
        }
    }
}
public class E31_IterableRandomShapeGenerator {
    public static void main(String[] args) {
        RandomShapeGenerator rsg = new RandomShapeGenerator(10);
        for(Shape shape : rsg)
            System.out.println(shape.getClass().getSimpleName());
    }
}