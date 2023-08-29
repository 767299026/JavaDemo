package Java.object.polymorphism;

//如果一个类中没有包含足够的信息来描绘一个具体的对象，这样的类就是抽象类
//抽象类不能实例化，但依然拥有成员变量，成员方法和构造方法，因为不能被实例化所以必须被继承使用
//一个类只能继承一个抽象类
public class Demo {
    public static void main(String[] args) {
        Salary s = new Salary("An qi", "ShenZhen", 3, 3600.00);
        Employee e = new Salary("John", "Shanghai", 2, 2400.00);

        System.out.println("通过Salary引用调用mailCheck----");
        s.mailCheck();

        System.out.println("\n 通过Employee引用调用mailCheck----");
        e.mailCheck();
    }
}
