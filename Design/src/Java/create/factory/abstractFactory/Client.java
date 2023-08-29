package Java.create.factory.abstractFactory;

public class Client {
    public static void main(String[] args) {

        System.out.println("============华为产品============");
        ProductFactory huaweiFactory = new HuaweiFactory();

        PhoneProduct huaweiPhone = huaweiFactory.createPhone();
        huaweiPhone.show();

        RouterProduct huaweiRouter = huaweiFactory.createRouter();
        huaweiRouter.show();

        System.out.println("============小米产品============");
        ProductFactory xiaomiFactory = new XiaomiFactory();

        PhoneProduct xiaomiPhone = xiaomiFactory.createPhone();
        xiaomiPhone.show();

        RouterProduct xiaomiRouter = xiaomiFactory.createRouter();
        xiaomiRouter.show();

    }
}
