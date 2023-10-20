package no.oslomet.cs.algdat;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class MinHeap<T> {
    ArrayList<T> internHeap;
    Comparator<? super T> sammenlikner;

    public static void main(String[] args) {
        MinHeap<Integer> minHeap = new MinHeap<>(Comparator.naturalOrder());

        minHeap.leggInn(3);
        minHeap.leggInn(5);
        minHeap.leggInn(2);
        minHeap.leggInn(7);
        minHeap.leggInn(1);
        minHeap.leggInn(2);
        System.out.println(minHeap);

        System.out.println(minHeap.hent());
        System.out.println(minHeap);
        while (!minHeap.tom()) {
            System.out.println(minHeap.hent());
        }
    }

    public String toString() {
        return internHeap.toString();
    }

    public MinHeap(Comparator<? super T> c) {
        internHeap = new ArrayList<>();
        internHeap.add(null);
        sammenlikner = c;
    }

    public int antall() {
        return internHeap.size()-1;
    }

    public boolean tom() {
        return antall() == 0;
    }

    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi);
        int n = internHeap.size();
        internHeap.add(verdi);
        if (n == 1) return true;
        while (sammenlikner.compare(verdi, internHeap.get(n/2)) < 0) {
            T forelder = internHeap.get(n/2);
            internHeap.set(n, forelder);
            internHeap.set(n/2, verdi);
            n = n/2;
            if (n == 1) break;
        }
        return true;
    }

    public T kikk() {
        return internHeap.get(1);
    }

    public T hent() {
        T utVerdi = internHeap.get(1);
        int n = 1;
        if (internHeap.size() == 2) {
            internHeap.remove(1);
            return utVerdi;
        }
        internHeap.set(1, internHeap.remove(internHeap.size()-1));
        while (n < internHeap.size()/2) {
            T current = internHeap.get(n);
            T venstreBarn = internHeap.get(2*n);
            T høyreBarn = internHeap.get(2*n+1);
            if (sammenlikner.compare(current, venstreBarn) < 0
                    && sammenlikner.compare(current, høyreBarn) < 0) break;
            if (sammenlikner.compare(venstreBarn, høyreBarn) <= 0) {
                internHeap.set(n, venstreBarn);
                internHeap.set(2*n, current);
                n = 2*n;
            } else {
                internHeap.set(n, høyreBarn);
                internHeap.set(2*n+1, current);
                n = 2*n+1;
            }
        }
        return utVerdi;
    }
}
