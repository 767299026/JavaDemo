package Java.behavior.observer;

//定义主题接口
public interface Subject {

    void registerObserver(Observer observer);

    void unregisterObserver(Observer observer);

    //同步阻塞实现
    void notifyObservers();

    //异步非阻塞实现
    //CompletableFuture<Void> notifyObservers();
}
