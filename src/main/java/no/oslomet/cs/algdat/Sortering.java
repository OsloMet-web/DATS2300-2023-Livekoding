package no.oslomet.cs.algdat;

public class Sortering {

    public static void main(String[] args) {
        int[] a = {13, 1, 11, 7, 2, 5, 3, 14, 15, 12 ,10, 4, 8, 6, 16, 9};
        int[] b = mergeSort(a);
        System.out.println(java.util.Arrays.toString(b));
    }

    public static int[] mergeSort(int[] a) {
        int n = a.length;
        System.out.println("Fått inn en liste. Den ser ut som" + java.util.Arrays.toString(a));
        if (n <= 1) {
            return a.clone();
        }

        int halvparten = n/2;
        int[] første = new int[halvparten];
        int[] siste = new int[n-halvparten];
        for (int i = 0; i < halvparten; ++i) {
            første[i] = a[i];
        }
        for (int i = 0; i < n-halvparten; ++i) {
            siste[i] = a[halvparten+i];
        }
        første = mergeSort(første);
        siste = mergeSort(siste);

        int[] sortert = new int[n];
        int i = 0; int j = 0; int k = 0;
        while (i < første.length && j < siste.length) {
            if (første[i] < siste[j]) {
                sortert[k++] = første[i++];
            } else {
                sortert[k++] = siste[j++];
            }
        }
        while (i < første.length) {
            sortert[k++] = første[i++];
        }
        while (j < siste.length) {
            sortert[k++] = siste[j++];
        }
        return sortert;
    }
}
