package generics;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/5 0005.
 */
class Container extends ArrayList<Product> {
    public Container(int nProducts){
        Generators.fill(this, Product.generator, nProducts);
    }
}

class CargoHold extends ArrayList<Container> {
    public CargoHold(int nContainers, int nProducts){
        for ( int i = 0; i < nContainers; i++ )
            add(new Container(nProducts));
    }
}

class Crane {
}

class CommandSection {
}

class CargoShip extends ArrayList<CargoHold> {
    private ArrayList<Crane> cranes = new ArrayList<Crane>();
    private CommandSection cmdSection = new CommandSection();

    public CargoShip(int nCargoHolds, int nContainers, int nProducts){
        for ( int i = 0; i < nCargoHolds; i++ )
            add(new CargoHold(nContainers, nProducts));
    }

    public String toString( ){
        StringBuilder result = new StringBuilder();
        for ( CargoHold ch : this )
            for ( Container c : ch )
                for ( Product p : c ) {
                    result.append(p);
                    result.append("\n");
                }
        return result.toString();
    }
}

public class E19_CargoShip {
    public static void main(String[] args){
        System.out.println(new CargoShip(14, 5, 10));
    }
}
