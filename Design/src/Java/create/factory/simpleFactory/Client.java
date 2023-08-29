package Java.create.factory.simpleFactory;

public class Client {

    public static void main(String[] args) {

        //获取产品A对象
        Product product_A = SimpleFactory.getProduct(EnumProductType.Product_A);

        Product product_AA = SimpleFactory.getProduct(EnumProductType.Product_A);

        //获取到的是不同的实例
        System.out.println(product_A + " " + product_AA);

        product_A.show();

        //获取产品B对象
        Product product_B = SimpleFactory.getProduct(EnumProductType.Product_B);

        product_B.show();
    }
}
