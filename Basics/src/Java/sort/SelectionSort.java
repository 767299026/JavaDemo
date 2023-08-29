package Java.sort;

//选择排序
//时间复杂度O(n²),空间复杂度O(1),不稳定
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {58, 24, 37, 11, 46, 79, 63};
        selectionSort(arr);
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 1 + i; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        for (int j : arr) {
            System.out.print(j);
            System.out.print(" ");
        }
    }
}
