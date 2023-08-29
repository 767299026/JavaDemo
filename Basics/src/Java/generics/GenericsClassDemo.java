package Java.generics;

//泛型类
public class GenericsClassDemo<T> {
    private T t;

    public void add(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public static void main(String[] args) {
        GenericsClassDemo<Integer> integerBox = new GenericsClassDemo<Integer>();
        GenericsClassDemo<String> stringBox = new GenericsClassDemo<String>();

        integerBox.add(100);
        stringBox.add(new String("Hello"));

        System.out.printf("整型值为 :%d\n", integerBox.get());
        System.out.printf("字符串为 :%s\n", stringBox.get());
    }
}
