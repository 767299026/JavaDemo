package Java.create.factory.simpleFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

//静态简单工厂模式
//简单工厂模式中,当对象的创建逻辑比较复杂时,所有的逻辑都容纳在一个工厂类，会导致工厂类很复杂,这种情况就应该使用工厂方法模式,将复杂的创建逻辑拆分到多个工厂类。
class SimpleFactory {


    //维护一个Key为类型,Value为Supplier函数式接口的Map
    //Supplier是一种懒汉式设计,只有真正调用时才会进行操作
    private static final Map<EnumProductType, Supplier<Product>> productMap = new HashMap<>();

    static {
        //这里并没有实例化对象,减小内存开销
        productMap.put(EnumProductType.Product_A, Product_A::new);
        productMap.put(EnumProductType.Product_B, Product_B::new);
        //在简单工厂模式中,想要新增产品种类就必须修改工厂的代码
        /*--------------此处添加新的种类---------------*/
    }

    //返回产品实例方法
    public static Product getProduct(EnumProductType productType) {
        Supplier<Product> productSupplier = productMap.get(productType);
        if (productSupplier == null) {
            throw new IllegalArgumentException("Invalid product type. 没有该产品类型");
        }
        //此处真正实例化对象，并返回
        return productSupplier.get();
    }
}
