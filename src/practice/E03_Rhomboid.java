package practice;

import polymorphism.shape.Circle;
import polymorphism.shape.Shape;
import polymorphism.shape.Square;
import polymorphism.shape.Triangle;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/12/28 0028.
 */
class Rhomboid extends Shape {
    public String toString( ){
        return "Rhomboid";
    }
}

public class E03_Rhomboid {
    public static void main(String[] args){
        List<Shape> shapes = Arrays.asList(
                new Circle(), new Square(), new Triangle(),
                new Rhomboid()
        );
        for ( Shape shape : shapes ) {
            shape.draw();
        }
        // Upcast to shape:
        Shape shape = new Rhomboid();
        shape.draw();
        shape.erase();
        // Downcast to Rhomboid
        Rhomboid r = ( Rhomboid ) shape;
        r.draw();
        r.toString();
        r.erase();

        // Downcast to Circle. Succeeds at compile time,
        // but fails at runtime with a ClassCastException:
        //! Circle c = (Circle)shape;

    }
}
