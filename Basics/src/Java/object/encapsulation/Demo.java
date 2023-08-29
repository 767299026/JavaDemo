package Java.object.encapsulation;

//封装是指一种将抽象性函数式接口的实现细节部分包装、隐藏起来的方法。
//封装可以防止类的代码和数据被外部类随机访问，适当的封装可以让程式码更容易理解和维护，也加强了安全性
public class Demo {
    public static void main(String[] args) {
        Person p = new Person();
        //只能通过Person类开放的接口操作属性
        p.setName("James");
        p.setAge(15);
        System.out.println(p);
    }
}

class Person {
    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}