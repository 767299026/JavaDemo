package Java.array;

//数组的声明与初始化
@SuppressWarnings("all")
public class ArrayDemo {
    public static void main(String[] args) {
        //数组为引用数据类型，数组中的元素可以是任何数据类型
        //数组对象会在内存中开辟一块连续空间，一旦数组初始化完成,长度就是确定的,无法更改

        //一维数组的声明
        //dataType[] array;
        int[] arr1;
        double[] arr2;
        //一维静态初始化，初始化与元素赋值同时进行
        arr1 = new int[]{1, 2, 3, 4, 5};
        String[] arr3 = new String[]{"Monday ", "TuesDay", "Wednesday", "Thursday", "Friday ", "Saturday", "SunDay"};

        //一维动态初始化，初始化与元素赋值分开进行
        arr2 = new double[3];
        arr2[0] = 2.5;
        arr2[1] = 3.5;
        arr2[2] = 4.5;

        //一维数组遍历输出
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + "\t");//打印输出1 2 3 4 5
        }
        System.out.println();
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + "\t");//打印输出 2.5 3.5 4.5
        }
        System.out.println();
        for (int i = 0; i < arr3.length; i++) {
            System.out.println(arr3[i] + "\t");//打印输出
        }

        //二维数组声明（一维数组中的数据类型为一维数组）
        //dataType[][] array
        int[][] arr4;
        String[][] arr6;

        //二维数组静态初始化
        arr4 = new int[][]{{3, 8, 2}, {2, 7}, {9, 0, 1, 6}};

        //二维数组动态初始化
        double[][] arr5 = new double[2][3];
        arr5[0][0] = 5.6;
        arr5[0][1] = 4.3;
        arr5[0][2] = 4.9;
        arr5[1][0] = 4.5;
        arr5[1][1] = 9.2;
        arr5[1][2] = 1.8;

        arr6 = new String[2][];
        arr6[0] = new String[2];
        arr6[1] = new String[3];
        arr6[0][0] = "Good";
        arr6[0][1] = "Luck";
        arr6[1][0] = "to";
        arr6[1][1] = "you";
        arr6[1][2] = "!";

        //二维数组遍历输出
        for (int i = 0; i < arr4.length; i++) {
            for (int j = 0; j < arr4[i].length; j++) {
                System.out.print(arr4[i][j] + "\t");//打印输出3 8 2 （换行） 2 7 （换行） 9 0 1 6
            }
            System.out.println();
        }
        for (int i = 0; i < arr5.length; i++) {
            for (int j = 0; j < arr5[i].length; j++) {
                System.out.print(arr5[i][j] + "\t");//打印输出5.6 4.3 4.9 （换行） 4.5	9.2	1.8
            }
            System.out.println();
        }
        for (int i = 0; i < arr6.length; i++) {
            for (int j = 0; j < arr6[i].length; j++) {
                System.out.print(arr6[i][j] + "\t");//打印输出Good luck （换行） to you !
            }
        }

    }
}
