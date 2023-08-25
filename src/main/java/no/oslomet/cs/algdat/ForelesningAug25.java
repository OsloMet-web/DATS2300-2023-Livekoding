package no.oslomet.cs.algdat;

public class ForelesningAug25 {

    public static int maks(int[] a, int fra, int til) {
        int maksindeks = fra;
        int maksverdi = a[fra];

        for (int i = fra; i < til; ++i) {
            if (a[i] > maksverdi) {
                maksindeks = i;
                maksverdi = a[i];
            }
        }
        return maksindeks;
    }

    public static int maks(int[] a) {
        return maks(a, 0, a.length);
    }

    public static void sorter(int[] a) {
        for (int i = a.length; i > 0; --i) {
            int maksindeks = maks(a, 0, i);
            bytt(a, maksindeks, i-1);
        }
    }

    public static void bytt(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
