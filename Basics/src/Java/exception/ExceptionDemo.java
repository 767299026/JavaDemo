package Java.exception;

//异常都是从java.lang.Exception类继承的子类
//异常类有两个主要的子类：IOException 类和 RuntimeException 类
public class ExceptionDemo extends Exception {
    public ExceptionDemo(String message) {//自定义
        super(message);
    }

    static int divide(int a, int b) throws ExceptionDemo {//抛出异常必须try catch捕获
        if (b == 0) {
            throw new ExceptionDemo("除数不能为0");//抛出异常
        }
        return a / b;
    }

    public static void main(String[] args) {
        try {
            int result = divide(10, 0);
            System.out.println(result);
        } catch (ExceptionDemo e) {
            System.out.println(e.getMessage());
        }
    }
}
