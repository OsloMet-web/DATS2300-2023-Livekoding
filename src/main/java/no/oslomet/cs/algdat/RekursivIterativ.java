package no.oslomet.cs.algdat;
import java.util.*;

public class RekursivIterativ {

    public static void main(String[] args) {
        int[] a = {5, 2, 3, 1, 7, 4, 2};

        kvikksort(a);
        System.out.println(Arrays.toString(a));

        //System.out.println(sumRekursiv(27750));
    }

    public static int sumRekursiv(int n) {
        if (n <= 0) throw new IllegalArgumentException("Ikke spør om sum av negative tall, please.");
        if (n == 1) return 1;
        return n + sumRekursiv(n-1);
    }

    public static void kvikksort(int[] a) {
        int[] stack = new int[2*a.length];
        int stackPointer = 0;
        stack[stackPointer++] = 0;
        stack[stackPointer++] = a.length-1;
        int left; int right;

        while (stackPointer > 0) {
            right = stack[--stackPointer];
            left = stack[--stackPointer];
            while (left < right) {
                bytt(a, left + (right - left) / 2, right);
                int pivot = a[right];

                int skille = oppdel(a, left, right - 1, pivot);
                bytt(a, right, skille);
                if (skille - left < right - skille) {
                    //kvikksort(a, left, skille - 1);
                    stack[stackPointer++] = left;
                    stack[stackPointer++] = skille-1;
                    left = skille + 1;
                } else {
                    //kvikksort(a, skille + 1, right);
                    stack[stackPointer++] = skille+1;
                    stack[stackPointer++] = right;
                    right = skille - 1;
                }
            }
        }
    }

    public static int oppdel(int[] a, int left, int right, int pivot) {
        int i = left;
        int j = right;
        while (true) {
            while (i <= j && a[i] < pivot) ++i;
            while (i <= j && a[j] >= pivot) --j;
            if (i >= j) break;
            bytt(a, i++, j--);
        }
        return i;
    }

    public static void bytt(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
