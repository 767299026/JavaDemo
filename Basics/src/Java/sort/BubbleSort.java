package Java.sort;

//冒泡排序算法
//时间复杂度O(n²),空间复杂度O(1),稳定
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {58, 24, 37, 11, 46, 79, 63};
        bubbleSort(arr);
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        for (int j : arr) {
            System.out.print(j);
            System.out.print(" ");
        }
    }
}
