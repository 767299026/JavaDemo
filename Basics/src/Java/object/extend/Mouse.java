package Java.object.extend;

//老鼠类，继承动物类
public class Mouse extends Animal {

    String name;

    public Mouse() {
        System.out.println("调用了老鼠类的无参构造器");
    }

    public Mouse(String name) {
        super("老鼠动物");
        System.out.println("调用了老鼠类的有参构造器: " + name);
        this.name = name;
    }

    void sleep() {
        System.out.println("子类方法：" + super.getName() + "正在睡");
    }

    void sleeping() {
        this.sleep();//this调用自己的方法
        super.sleep();//super调用父类的方法
    }
}
