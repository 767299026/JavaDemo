package Java.behavior.observer;

//具体观察者
//此处执行了接收到用户注册的通知后进行短信和邮箱发送
public class ConcreteObserver implements Observer {

    @Override
    public void update(User user) {
        System.out.println("已发送短信通知给用户: " + user.getName() + "，用户手机号码为: " + user.getPhoneNumber());
        System.out.println("已发送邮件通知给用户: " + user.getName() + "，用户邮件地址为: " + user.getEmail());
    }
}
