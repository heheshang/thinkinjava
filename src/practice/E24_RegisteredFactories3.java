package practice;

import net.mindview.util.Null;
import typeinfo.factory.Factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

interface IPart {
}

class NullPartProxyHandler implements InvocationHandler {
    private String nullName;
    private IPart proxied = new NPart();

    NullPartProxyHandler(Class<? extends IPart> type){
        nullName = type.getSimpleName() + ": [Null Part]";
    }

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable{
        return method.invoke(proxied, args);
    }

    private class NPart implements Null, IPart {
        public String toString( ){
            return nullName;
        }
    }
}

class Part3 implements IPart {
    static List<Factory<IPart>> partFactories =
            new ArrayList<Factory<IPart>>();
    private static Random rand = new Random(47);

    static{
        partFactories.add(new FuelFilter3.Factory());
        partFactories.add(new AirFilter3.Factory());
        partFactories.add(new CabinAirFilter3.Factory());
        partFactories.add(new OilFilter3.Factory());
        partFactories.add(new FanBelt3.Factory());
        partFactories.add(new PowerSteeringBelt3.Factory());
        partFactories.add(new GeneratorBelt3.Factory());
    }

    public static IPart newNull(Class<? extends IPart> type){
        return ( IPart ) Proxy.newProxyInstance(
                IPart.class.getClassLoader(),
                new Class<?>[]{Null.class, IPart.class},
                new NullPartProxyHandler(type));
    }

    public static IPart createRandom( ){
        int n = rand.nextInt(partFactories.size());
        return partFactories.get(n).create();
    }

    public String toString( ){
        return getClass().getSimpleName();
    }
}

class Filter3 extends Part3 {
}

class FuelFilter3 extends Filter3 {
    public static class Factory
            implements typeinfo.factory.Factory<IPart> {
        public IPart create( ){
            return new FuelFilter3();
        }
    }
}

class AirFilter3 extends Filter3 {
    public static class Factory
            implements typeinfo.factory.Factory<IPart> {
        public IPart create( ){
            return new AirFilter3();
        }
    }
}

class CabinAirFilter3 extends Filter3 {
    public static class Factory implements typeinfo.factory.Factory<IPart> {
        public IPart create( ){
            return new CabinAirFilter3();
        }
    }
}

class OilFilter3 extends Filter3 {
    public static class Factory implements typeinfo.factory.Factory<IPart> {
        public IPart create( ){
            return new OilFilter3();
        }
    }
}

class Belt3 extends Part3 {
}

class FanBelt3 extends Belt3 {
    public static class Factory implements typeinfo.factory.Factory<IPart> {
        public IPart create( ){
            return new FanBelt3();
        }
    }
}

class GeneratorBelt3 extends Belt3 {
    public static class Factory implements typeinfo.factory.Factory<IPart> {
        public IPart create( ){
            return new GeneratorBelt3();
        }
    }
}

class PowerSteeringBelt3 extends Belt3 {
    public static class Factory implements typeinfo.factory.Factory<IPart> {
        public IPart create( ){
            return new PowerSteeringBelt3();
        }
    }
}

public class E24_RegisteredFactories3 {
    public static void main(String[] args){
        for ( int i = 0; i < 10; i++ ) {
            IPart part = Part3.createRandom();
// Real object
            System.out.println(part);
// Null companion
            System.out.println(Part3.newNull(part.getClass()));
        }
    }
} /* Output:
Thinking in Java, 4 th Edition Annotated Solution Guide  324
GeneratorBelt3
GeneratorBelt3: [Null Part]
CabinAirFilter3
CabinAirFilter3: [Null Part]
GeneratorBelt3
GeneratorBelt3: [Null Part]
AirFilter3
AirFilter3: [Null Part]
PowerSteeringBelt3
PowerSteeringBelt3: [Null Part]
CabinAirFilter3
CabinAirFilter3: [Null Part]
FuelFilter3
FuelFilter3: [Null Part]
PowerSteeringBelt3
PowerSteeringBelt3: [Null Part]
PowerSteeringBelt3
PowerSteeringBelt3: [Null Part]
FuelFilter3
FuelFilter3: [Null Part]
*///:~
   /* Following TIJ4’s technique, we use a Dynamic Proxy to create Null Objects for
        different parts. The IPart interface is the most important innovation to
        RegisteredFactories.java. Notice that the rest of the class hierarchy is
        unchanged.*/