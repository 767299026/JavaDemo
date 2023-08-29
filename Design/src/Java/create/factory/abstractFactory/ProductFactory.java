package Java.create.factory.abstractFactory;

abstract class ProductFactory {

    abstract PhoneProduct createPhone();

    abstract RouterProduct createRouter();
}

class HuaweiFactory extends ProductFactory {

    @Override
    PhoneProduct createPhone() {
        return new HuaweiPhone();
    }

    @Override
    RouterProduct createRouter() {
        return new HuaweiRouter();
    }
}

class XiaomiFactory extends ProductFactory {

    @Override
    PhoneProduct createPhone() {
        return new XiaomiPhone();
    }

    @Override
    RouterProduct createRouter() {
        return new XiaomiRouter();
    }
}