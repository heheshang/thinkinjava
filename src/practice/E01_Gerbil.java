package practice;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/24 0024.
 */
class Gerbil {
    private final int gerbilNumber;

    Gerbil(int gerbilNumber){
        this.gerbilNumber = gerbilNumber;
    }

    public String toString( ){
        return "gerbil" + gerbilNumber;
    }

    public void hop( ){
        System.out.println(this + " is hoping");
    }
}

public class E01_Gerbil {
    public static void main(String[] args){
        ArrayList<Gerbil> gerbils = new ArrayList<>();
        for ( int i = 0; i < 10; i++ ) {
            gerbils.add(new Gerbil(i));
        }
        for ( Gerbil gerbil : gerbils ) {
            gerbil.hop();
        }
    }
}
