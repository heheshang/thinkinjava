package practice;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/3/20 0020.
 */
abstract class Shape implements Serializable {
    public static final int RED = 1, BLUE = 2, GREEN = 3;
    private static Random rand = new Random(47);
    private static int counter = 0;
    private int xPos, yPos, dimension;

    public Shape(int xVal, int yVal, int dim){
        xPos = xVal;
        yPos = yVal;
        dimension = dim;
    }

    public static Shape randomFactory( ){
        int xVal = rand.nextInt(100);
        int yVal = rand.nextInt(100);
        int dim = rand.nextInt(100);
        switch ( counter++ % 3 ) {
            default:
            case 0:
                return new Circle(xVal, yVal, dim);
            case 1:
                return new Square(xVal, yVal, dim);
            case 2:
                return new Line(xVal, yVal, dim);
        }
    }

    public abstract int getColor( );

    public abstract void setColor(int newColor);

    public String toString( ){
        return getClass() +
                "color[" + getColor() + "] xPos[" + xPos +
                "] yPos[" + yPos + "] dim[" + dimension + "]\n";
    }
}

class Circle extends Shape {
    private static int color;

    public Circle(int xVal, int yVal, int dim){
        super(xVal, yVal, dim);
    }

    public static void
    serializeStaticState(ObjectOutputStream os)
            throws IOException{
        os.writeInt(color);
    }

    public static void
    deserializeStaticState(ObjectInputStream os)
            throws IOException{
        color = os.readInt();
    }

    public int getColor( ){
        return color;
    }

    public void setColor(int newColor){
        color = newColor;
    }
}

class Square extends Shape {
    private static int color;

    public Square(int xVal, int yVal, int dim){
        super(xVal, yVal, dim);
    }

    public static void
    serializeStaticState(ObjectOutputStream os)
            throws IOException{
        os.writeInt(color);
    }

    public static void
    deserializeStaticState(ObjectInputStream os)
            throws IOException{
        color = os.readInt();
    }

    public int getColor( ){
        return color;
    }

    public void setColor(int newColor){
        color = newColor;
    }
}

class Line extends Shape {
    private static int color;

    public Line(int xVal, int yVal, int dim){
        super(xVal, yVal, dim);
    }

    public static void
    serializeStaticState(ObjectOutputStream os)
            throws IOException{
        os.writeInt(color);
    }

    public static void
    deserializeStaticState(ObjectInputStream os)
            throws IOException{
        color = os.readInt();
    }

    public int getColor( ){
        return color;
    }

    public void setColor(int newColor){
        color = newColor;
    }
}

public class E30_RepairCADState {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception{
        List<Shape> shapes = new ArrayList<Shape>();
// Make some shapes:
        for ( int i = 0; i < 10; i++ )
            shapes.add(Shape.randomFactory());
// Set all the static colors to GREEN:
        for ( int i = 0; i < 10; i++ )
            ( ( Shape ) shapes.get(i) ).setColor(Shape.GREEN);
// Save the state vector:
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("CADState.out"));
        Circle.serializeStaticState(out);
        Square.serializeStaticState(out);
        Line.serializeStaticState(out);
        out.writeObject(shapes);
// Display the shapes:
        System.out.println(shapes);
// Now read the file back in:
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("CADState.out"));
// Read in the same order they were written:
        Circle.deserializeStaticState(in);
        Square.deserializeStaticState(in);
        Line.deserializeStaticState(in);
        shapes = ( List<Shape> ) in.readObject();
        System.out.println(shapes);
    }
} /* Output:
[class io.Circlecolor[3] xPos[58] yPos[55] dim[93]
, class io.Squarecolor[3] xPos[61] yPos[61] dim[29]
, class io.Linecolor[3] xPos[68] yPos[0] dim[22]
, class io.Circlecolor[3] xPos[7] yPos[88] dim[28]
, class io.Squarecolor[3] xPos[51] yPos[89] dim[9]
, class io.Linecolor[3] xPos[78] yPos[98] dim[61]
, class io.Circlecolor[3] xPos[20] yPos[58] dim[16]
, class io.Squarecolor[3] xPos[40] yPos[11] dim[22]
, class io.Linecolor[3] xPos[4] yPos[83] dim[6]
, class io.Circlecolor[3] xPos[75] yPos[10] dim[42]
]
[class io.Circlecolor[3] xPos[58] yPos[55] dim[93]
, class io.Squarecolor[3] xPos[61] yPos[61] dim[29]
, class io.Linecolor[3] xPos[68] yPos[0] dim[22]
, class io.Circlecolor[3] xPos[7] yPos[88] dim[28]
Thinking in Java, 4 th Edition Annotated Solution Guide  540
, class io.Squarecolor[3] xPos[51] yPos[89] dim[9]
, class io.Linecolor[3] xPos[78] yPos[98] dim[61]
, class io.Circlecolor[3] xPos[20] yPos[58] dim[16]
, class io.Squarecolor[3] xPos[40] yPos[11] dim[22]
, class io.Linecolor[3] xPos[4] yPos[83] dim[6]
, class io.Circlecolor[3] xPos[75] yPos[10] dim[42]
]
*///:~