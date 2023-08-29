package Java.create.singleton;

//静态内部类实现单例模式
public class SingletonFour {

    //私有化构造方法,防止被实例化
    private SingletonFour() {
    }

    //使用一个静态内部类来维护单例
    private static class SingletonHolder {
        private static SingletonFour instance = new SingletonFour();
    }

    //获取实例
    public static SingletonFour getInstance() {
        return SingletonHolder.instance;
    }

    //如果该对象被用于序列化,可以保证对象在序列化前后保持一致
    public Object readResolve() {
        return getInstance();
    }
}
