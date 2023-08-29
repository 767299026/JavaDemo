package Java.create.factory.abstractFactory;

abstract class PhoneProduct {

    protected String name;

    protected PhoneProduct(String name) {
        this.name = name;
    }

    public abstract void show();
}

class HuaweiPhone extends PhoneProduct {

    public HuaweiPhone() {
        super("华为手机");
    }

    @Override
    public void show() {
        System.out.println("生产了" + name);
    }
}

class XiaomiPhone extends PhoneProduct {

    public XiaomiPhone() {
        super("小米手机");
    }

    @Override
    public void show() {
        System.out.println("生产了" + name);
    }
}