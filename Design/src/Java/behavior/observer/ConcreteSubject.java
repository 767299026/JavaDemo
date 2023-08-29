package Java.behavior.observer;

import java.util.ArrayList;
import java.util.List;

//具体主题实现类
//此处为用户注册实现
public class ConcreteSubject implements Subject {

    private final List<Observer> observers = new ArrayList<>();
    private User user;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    //同步阻塞实现方式
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(getUser());
        }
    }

    //异步实现
    /*@Override
    public CompletableFuture<Void> notifyObservers() {
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (Observer observer : observers) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> observer.update(getUser()));
            futures.add(future);
        }
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }*/

    //用户注册方法,这里通知观察者用户注册了
    public void registerUser(String name, String phoneNumber, String email) {
        //执行用户注册逻辑
        setUser(new User(name, phoneNumber, email));
        //通知观察者
        notifyObservers();
    }

    //设置用户信息
    private void setUser(User user) {
        this.user = user;
    }

    //获取用户信息
    private User getUser() {
        return user;
    }
}

