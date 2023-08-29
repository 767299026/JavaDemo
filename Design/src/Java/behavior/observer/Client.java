package Java.behavior.observer;

//观察者模式使用类
public class Client {
    public static void main(String[] args) {
        //创建被观察者对象
        ConcreteSubject subject = new ConcreteSubject();
        //创建观察者对象
        Observer observer = new ConcreteObserver();
        //注册观察者
        subject.registerObserver(observer);

        //同步阻塞用户注册触发观察者响应
        subject.registerUser("Alice", "123456", "alice@alice.com");
        subject.registerUser("Tom", "123456", "Tom@tom.com");

        //异步非阻塞用户注册触发观察者响应
        /*subject.registerUser("Alice", "123456", "alice@alice.com")
                .thenRun(() -> System.out.println("用户注册完成"))
                .join();
        subject.registerUser("Tom", "123456", "Tom@tom.com")
                .thenRun(() -> System.out.println("用户注册完成"))
                .join();*/
    }
}
