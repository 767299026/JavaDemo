package Java.structure.adapter;

//适配器模式使用类
public class Client {
    public static void main(String[] args) {
        Target adapter220V = new Adapter();
        int Voltage = adapter220V.Convert_110V();
        System.out.println("当前工作电压: " + Voltage + " V");
    }
}
