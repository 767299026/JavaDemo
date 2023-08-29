package Java.object.abstraction;

//多态即一个对象在不同条件下所表现的不同形式
//子类和父类存在同名成员变量，访问父类的成员变量
//子类和父类存在同名的非静态成员方法，访问子类重写的方法
//子类和父类存在同名的静态成员变量和成员方法，访问父类的成员函数
//多态情况下不可以访问子类独有的方法
public class Demo {
    public static void main(String[] args) {
        Salary s = new Salary("员工 A", "北京", 3, 3600.00);
        Employee e = new Salary("员工 B", "上海", 2, 2400.00);//向上转型(父类引用指向子类对象)
        System.out.println("使用 Salary 的引用调用 mailCheck -- ");
        s.mailCheck();
        System.out.println("\n使用 Employee 的引用调用 mailCheck--");
        e.mailCheck();//子类方法已重写,此处会调用Salary的mailCheck()
    }
}
