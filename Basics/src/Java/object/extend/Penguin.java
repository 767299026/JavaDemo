package Java.object.extend;

//企鹅类，继承动物类
public class Penguin extends Animal {

    String name;

    public Penguin() {
        System.out.println("调用了企鹅类的无参构造器");
    }

    public Penguin(String name) {
        super("企鹅动物");
        System.out.println("调用了企鹅类的有参构造器: " + name);
        this.name = name;
    }

    void introduction() {
        System.out.println("大家好！我是 " + name);
    }
}
