package Java.structure.garnisher;

//被装饰者,要接收附加装饰的基类
public class SimpleCoffee implements Coffee {

    @Override
    public double getCost() {
        return 10.0;
    }

    @Override
    public String getDescription() {
        return "简单咖啡";
    }
}
