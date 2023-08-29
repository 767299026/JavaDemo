package Java.sort;

//希儿排序算法
//时间复杂度O(nlogn),空间复杂度O(1),不稳定
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = {58, 24, 37, 11, 46, 79, 63};
        shellSort(arr);
    }

    public static void shellSort(int[] arr) {

        int temp;
        for (int step = arr.length / 2; step >= 1; step /= 2) {
            for (int i = step; i < arr.length; i++) {
                temp = arr[i];
                int j = i - step;
                while (j >= 0 && arr[j] > temp) {
                    arr[j + step] = arr[j];
                    j -= step;
                }
                arr[j + step] = temp;
            }
        }
        for (int j : arr) {
            System.out.print(j);
            System.out.print(" ");
        }
    }
}