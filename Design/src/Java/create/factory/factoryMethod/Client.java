package Java.create.factory.factoryMethod;

public class Client {
    public static void main(String[] args) {


        //获取产品A对象
        Product product_A = new ConcreteFactoryA().createProduct();
        Product product_AA = new ConcreteFactoryA().createProduct();

        //获取到的是不同的实例
        System.out.println(product_A + " " + product_AA);

        product_A.show();

        //获取产品B对象
        Product product_B = new ConcreteFactoryB().createProduct();

        product_B.show();
    }
}
