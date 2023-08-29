package Java.create.singleton;

//懒汉式单例模式(线程不安全)
public class SingletonTwo {

    //创建一个实例对象
    private static SingletonTwo instance;

    //私有化构造方法，防止被实例化
    private SingletonTwo() {
    }

    ;

    //第一次调用才会实例化对象
    public static SingletonTwo getInstance() {
        if (instance == null) {
            instance = new SingletonTwo();
        }
        return instance;
    }
}
