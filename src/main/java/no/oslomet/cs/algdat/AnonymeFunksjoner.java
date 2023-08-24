package no.oslomet.cs.algdat;
import java.util.Arrays;

public class AnonymeFunksjoner {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 7};
        System.out.println(Arrays.toString(a));
        int[] b = doble(a);
        System.out.println(Arrays.toString(b));
        int[] c = kvadrer(a);
        System.out.println(Arrays.toString(c));
        int[] d = Hjelpefunksjoner.avbild(a, (x) -> 2*x+3);
        System.out.println(Arrays.toString(d));
        long t = Hjelpefunksjoner.taTiden(() -> tarLittTid(10_000_000));
        System.out.println(t);

        Hjelpefunksjoner.Avbildningsfunksjon f = (x) -> x*x;
        System.out.println(f.bruk(3));

        int[] e = Arrays.stream(a).map((x)->(x*x)).toArray();
        System.out.println(Arrays.toString(e));
    }

    public static void tarLittTid(int n) {
        for (int i = 0; i< n; ++i) {
            int j = i*i*i*i;
        }
    }

    public static int[] doble(int[] a) {
        int n = a.length;
        int[] b = new int[n];
        for (int i = 0; i < n; ++i ) {
            b[i] = a[i];
        }
        return b;
    }

    public static int[] kvadrer(int[] a) {
        int n = a.length;
        int[] b = new int[n];
        for (int i = 0; i < n; ++i) {
            b[i] = a[i]*a[i];
        }
        return b;
    }
}












class Hjelpefunksjoner {

    @FunctionalInterface
    interface Avbildningsfunksjon {
        int bruk(int a);
    }

    @FunctionalInterface
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
