package Java.java8;

//接口的默认方法
public class DefaultMethodDemo {
    public static void main(String args[]) {

        Vehicle vehicle = new Car();
        vehicle.print();
    }
}

//载具接口
interface Vehicle {
    default void print() {
        System.out.println("我是一辆车!");
    }

    static void blowHorn() {
        System.out.println("按喇叭!!!");
    }
}

//轮子接口
interface FourWheeler {
    default void print() {
        System.out.println("我是一辆四轮车!");
    }
}

//汽车类实现载具和轮子接口
class Car implements Vehicle, FourWheeler {
    public void print() {
        Vehicle.super.print();
        FourWheeler.super.print();
        Vehicle.blowHorn();
        System.out.println("我是一辆汽车!");
    }
}
