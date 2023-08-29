package Java.create.singleton;

//双检锁优化懒汉式单例模式
public class SingletonThree {

    private volatile static SingletonThree instance = null;

    private SingletonThree() {
    }

    public static SingletonThree getInstance() {
        //先检查实例是否存在,如果不存在才进入下面的同步块
        if (instance == null) {
            //同步块,线程安全的创建实例
            synchronized (SingletonThree.class) {
                //
                if (instance == null) {
                    instance = new SingletonThree();
                }
            }
        }
        return instance;
    }
}
