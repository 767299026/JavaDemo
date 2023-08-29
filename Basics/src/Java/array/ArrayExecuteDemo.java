package Java.array;

import java.util.Arrays;

//数组的一些操作
@SuppressWarnings("all")
public class ArrayExecuteDemo {
    public static void main(String[] args) {

        int[] arr1 = new int[]{3, 5, 7, 9};
        String[][] arr2 = new String[][]{{"Good", "Luck"}, {"to", "you", "!"}};

        //计算数值数组元素总和
        int total = 0;
        for (int i = 0; i < arr1.length; i++) {
            total += arr1[i];
        }
        System.out.println("arr1数组的和为： " + total);//打印输出24

        //查找数值数组中最大的元素值
        int max = arr1[0];
        for (int i = 1; i < arr1.length; i++) {
            if (arr1[i] > max)
                max = arr1[i];
        }
        System.out.println("arr1数组中最大值为： " + max);//打印输出 9

        //数组的工具类Arrays类使用
        double[] arr3 = new double[]{5.6, 4.3, 13.2, 45.4, 21.0};
        int index = Arrays.binarySearch(arr3, 13.2);//查找该数组是否拥有指定元素
        System.out.println(index);//打印输出 2，说明要找的元素在数组的下标为2

        Arrays.sort(arr3);//对数值数组进行排序
        for (int i = 0; i < arr3.length; i++) {
            System.out.print(arr3[i] + "\t");//打印输出 4.3 5.6 13.2 21.0 45.4
        }

        System.out.println();

        Arrays.fill(arr3, 0.0);//对数组进行元素填充
        for (int i = 0; i < arr3.length; i++) {
            System.out.print(arr3[i] + "\t");//打印输出 0.0 0.0 0.0 0.0
        }
    }
}