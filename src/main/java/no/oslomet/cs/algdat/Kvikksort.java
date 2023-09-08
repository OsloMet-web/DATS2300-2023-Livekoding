package no.oslomet.cs.algdat;
import java.util.*;
public class Kvikksort {
    public static void tempMain() {
        char[] a = {'f', 'l', 'q'};
        int i = partisjonerVokaler(a);
        System.out.println(Arrays.toString(a));
        System.out.println(i);
        int[] b = {4, 3, 2, 5, 7, 2, 9, 4, 3};
        int[]c = b.clone();
        kvikksort(b);
        System.out.println(Arrays.toString(b));
        iCantBelieveThisSorts(c);
        System.out.println(Arrays.toString(c));
    }

    public static void main(String[] args) {
        //tempMain();
        int n = 1_000_000;
        int[] rP = randPerm(n);
        iCantBelieveThisSorts(rP);
    }


    public static int[] randPerm(int n) {
        Random r = new Random();
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) a[i] = i+1;
        for (int k = n-1; k > 0; --k) {
            int i = r.nextInt(k+1);
            bytt(a, i, k);
        }
        return a;
    }

    public static void kvikksort(int[] a) {
        kvikksort(a, 0, a.length-1);
    }

    private static void kvikksort(int[] a, int v, int h) {
        // må vite når vi stopper
        if (v >= h) return;

        // først partisjoner lista
        bytt(a, h, v + (h-v)/2); // Hvorfor ikke (v+h)/2? Svar: Integer Overflow.
        int pivot = a[h];
        int i = v;
        int j = h-1;
        while (true) {
            while (i <= j && a[i] < pivot) i++;
            while (i <= j && a[j] >= pivot) j--;
            if (i >= j) break;
            bytt(a, i++, j--);
        }
        bytt(a, i, h);

        kvikksort(a, v, i-1);
        kvikksort(a, i+1, h);
    }

    public static void iCantBelieveThisSorts(int[] a) {
        for (int i = 0; i < a.length; ++i) {
            for (int j = 0; j < a.length; j++) {
                if (a[i] < a[j]) bytt(a, i, j);
            }
        }
    }


    public static int partisjonerVokaler(char[] a) {
        int v = 0;
        int h = a.length-1;
        while (true) {
            while (v <= h && erVokal(a[v])) ++v;
            while (v <= h && !erVokal(a[h])) --h;
            if (v >= h) break;
            bytt(a, v++, h--);
        }
        return v;
    }

    public static void bytt(char[] a, int i, int j) {
        char tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void bytt(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static boolean erVokal(char c) {
        char[] vokaler = {'a', 'e', 'i', 'o', 'u', 'y', 'æ', 'ø', 'å'};
        boolean boolVokal = false;
        for (char v : vokaler) {
            if (c == v) {
                boolVokal = true;
                break;
            }
        }
        return boolVokal;
    }
}
