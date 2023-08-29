package Java.create.factory.abstractFactory;

abstract class RouterProduct {

    protected String name;

    protected RouterProduct(String name) {
        this.name = name;
    }

    public abstract void show();
}

class HuaweiRouter extends RouterProduct {

    public HuaweiRouter() {
        super("华为路由器");
    }

    @Override
    public void show() {
        System.out.println("生产了" + name);
    }
}

class XiaomiRouter extends RouterProduct {

    public XiaomiRouter() {
        super("小米路由器");
    }

    @Override
    public void show() {
        System.out.println("生产了" + name);
    }
}
