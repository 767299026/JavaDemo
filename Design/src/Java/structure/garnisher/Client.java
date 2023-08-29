package Java.structure.garnisher;

//装饰者模式使用类
public class Client {
    public static void main(String[] args) {

        //简单咖啡
        Coffee coffee = new SimpleCoffee();
        System.out.println("描述: " + coffee.getDescription() + ",价格: " + coffee.getCost() + "元");
        //加牛奶
        Coffee coffeeWithMilk = new Milk(coffee);
        System.out.println("描述: " + coffeeWithMilk.getDescription() + ",价格: " + coffeeWithMilk.getCost() + "元");
        //加牛奶和糖
        Coffee coffeeWithMilkAndSugar = new Sugar(coffeeWithMilk);
        System.out.println("描述: " + coffeeWithMilkAndSugar.getDescription() + ",价格: " + coffeeWithMilkAndSugar.getCost() + "元");
        //加牛奶和糖和香草
        Coffee coffeeWithMilkAndSugarAndVanilla = new Vanilla(coffeeWithMilkAndSugar);
        System.out.println("描述: " + coffeeWithMilkAndSugarAndVanilla.getDescription() + ",价格: " + coffeeWithMilkAndSugarAndVanilla.getCost() + "元");
    }
}
