package Java.create.factory.factoryMethod;

//抽象工厂类,定义具体工厂的公共接口
abstract class Factory {
    public abstract Product createProduct();
}

//具体生产产品A工厂类
class ConcreteFactoryA extends Factory {
    @Override
    public Product createProduct() {
        return new Product_A();
    }
}

//具体生产产品B工厂类
class ConcreteFactoryB extends Factory {
    @Override
    public Product createProduct() {
        return new Product_B();
    }
}

//添加新的生产工厂类