package Java.structure.adapter;

public class Adapter extends Adaptee implements Target {

    //期待的插头要求输出110V，但原有插头只能输出220V
    //因此适配器进行包装处理

    @Override
    public int Convert_110V() {
        return this.Output_220V() / 2;
    }
}
