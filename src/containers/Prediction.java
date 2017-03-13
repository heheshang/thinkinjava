package containers;//: containers/Prediction.java
// Predicting the weather with groundhogs.

import java.util.*;

public class Prediction {
    private static Random rand = new Random(47);
    private boolean shadow = rand.nextDouble() > 0.5;

    public String toString( ){
        if ( shadow )
            return "Six more weeks of Winter!"+shadow+",rand.nextDouble()"+rand.nextDouble();
        else
            return "Early Spring!"+shadow+",rand.nextDouble()"+rand.nextDouble();
    }
} ///:~
