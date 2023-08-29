package Java.java8;

//([Lambda参数列表，即形参列表]) -> {Lambda体，即方法体}
//Lambda表达式，允许把函数作为一个方法的参数，可以使代码变得更加简洁紧凑
//有且只有一个抽象方法的接口为函数式接口，才能赋值为lambda表达式
public class LambadaDemo {
    public static void main(String[] args) {
        //创建类实现Runnable接口开启线程
        Thread thread = new Thread(new MyRunnable());
        thread.start();

        //lambda表达式实现开启线程
        new Thread(() -> System.out.println("Hello Lambda")).start();
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello");
    }
}
