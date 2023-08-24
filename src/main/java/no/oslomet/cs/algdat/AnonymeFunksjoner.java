package no.oslomet.cs.algdat;
import java.util.Arrays;

public class AnonymeFunksjoner {
    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        System.out.println(Arrays.toString(a));
        int[] b = doble(a);
        System.out.println(Arrays.toString(a));
        int[] c = kvadrer(a);
        System.out.println(Arrays.toString(a));
    }

    public static int[] doble(int[] a) {
        int n = a.length;
        int[] b = new int[n];
        for (int i = 0; i < n; ++i ) {
            b[i] = 2*a[i];
        }
        return b;
    }

    public static int[] kvadrer(int[] a) {
        int n = a.length;
        int[] b = new int[n];
        for (int i = 0; i < n; ++i) {
            b[i] = a[i]^2;
        }
        return b;
    }
}













class Hjelpefunksjoner {
    interface Avbildningsfunksjon {
        int bruk(int a);
    }

    interface Tidtaker {
        void bruk();
    }

    public static int[] avbild(int[] a, Avbildningsfunksjon f) {
        int[] b = new int[a.length];
        for (int i = 0; i < b.length; ++i) {
            b[i] = f.bruk(a[i]);
        }
        return b;
    }

    public static long taTiden(Tidtaker t) {
        long tid = System.currentTimeMillis();
        t.bruk();
        tid = System.currentTimeMillis() - tid;
        return tid;
    }
}
