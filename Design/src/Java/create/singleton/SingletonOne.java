package Java.create.singleton;

//饿汉式单例模式(线程安全)
public class SingletonOne {

    //创建一个实例对象
    private static SingletonOne instance = new SingletonOne();

    //私有化构造方法，防止被实例化
    private SingletonOne() {
    }

    public static SingletonOne getInstance() {
        return instance;
    }
}
