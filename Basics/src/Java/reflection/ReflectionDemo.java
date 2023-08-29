package Java.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

//Java的反射机制是指在运行时动态地获取类信息,并且能通过这些信息来操作该类的对象
public class ReflectionDemo {
    public static void main(String[] args) throws Exception {

        // 获取Person类的Class对象
        Class<Person> personClass = Person.class;

        // 获取Person类的所有构造方法
        Constructor<?>[] constructors = personClass.getConstructors();
        System.out.println("Person类的构造方法：");
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }

        // 获取Person类的所有方法
        Method[] methods = personClass.getMethods();
        System.out.println("Person类的方法：");
        for (Method method : methods) {
            System.out.println(method);
        }

        // 获取Person类的所有字段
        Field[] fields = personClass.getFields();
        System.out.println("Person类的字段：");
        for (Field field : fields) {
            System.out.println(field);
        }

        // 创建一个Person类的对象
        Person person = personClass.newInstance();
        System.out.println("创建的Person对象：" + person);

        // 获取Person类中的setName方法，并调用该方法
        Method setNameMethod = personClass.getMethod("setName", String.class);
        setNameMethod.invoke(person, "Tom");
        System.out.println("调用setName方法后：" + person);

        // 获取Person类中的name字段，并设置该字段的值
        Field nameField = personClass.getField("name");
        nameField.set(person, "Jerry");
        System.out.println("设置name字段后：" + person);
    }
}

class Person {
    public String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
