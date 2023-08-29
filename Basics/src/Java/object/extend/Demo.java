package Java.object.extend;

//继承是从已有的类派生出新的类，新类能吸收已有类的数据属性和行为，并扩展新的能力
//继承减少了代码的冗余，提高了代码的复用性，且为Java的多态性提供了前提
public class Demo {

    public static void main(String[] args) {

        Penguin penguin1 = new Penguin();
        Penguin penguin2 = new Penguin("南极企鹅");
        penguin2.introduction();//由于企鹅类重写了父类的introduction，此处打印penguin2的私有name字段值

        Mouse mouse1 = new Mouse();
        Mouse mouse2 = new Mouse("北地老鼠");
        mouse2.introduction();//由于老鼠类没有重写父类的introduction，此处打印mouse2的父类name字段值
        mouse2.sleeping();
    }
}
