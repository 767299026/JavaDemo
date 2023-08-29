package Java.object.extend;

//Java不支持一个类同时继承多个类
//公共父类
public class Animal {

    private String name;

    public Animal() {
        System.out.println("调用了父类的无参构造器");
    }

    public Animal(String myName) {
        name = myName;
        System.out.println("调用了父类的有参构造器");
    }

    public String getName() {
        return name;
    }

    void eat() {
        System.out.println(name + "正在吃");
    }

    void sleep() {
        System.out.println("父类方法： " + name + "正在睡");
    }

    void introduction() {
        System.out.println("大家好！我是 " + name);
    }
}