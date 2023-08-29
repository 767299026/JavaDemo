package Java.java8;

import java.util.ArrayList;

//方法引用允许使用方法名指向一个方法
//方法引用使用一对冒号::
public class MethodReferenceDemo {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();

        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");

        names.forEach(System.out::println);
    }
}
