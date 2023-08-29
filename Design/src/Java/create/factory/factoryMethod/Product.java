package Java.create.factory.factoryMethod;

//创建抽象产品类，定义具体产品的公共接口
abstract class Product {

    protected String name;

    protected Product(String name) {
        this.name = name;
    }

    public abstract void show();
}

//具体产品A类
class Product_A extends Product {

    //A类产品的属性……

    public Product_A() {
        super("A类保温杯");
    }

    @Override
    public void show() {
        System.out.println(name);
    }
}

//具体产品B类
class Product_B extends Product {

    //B类产品的属性……

    public Product_B() {
        super("B类保温杯");
    }

    @Override
    public void show() {
        System.out.println(name);
    }
}