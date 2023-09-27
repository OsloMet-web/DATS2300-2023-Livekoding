package no.oslomet.cs.algdat;

import java.util.Arrays;
import java.util.Iterator;

public class UendeligeMengder {
    public static void main(String[] args) {
        int teller = 0;
        for (int current : new Primtall()) {
            if (++teller > 10) break;
            System.out.println(current);
        }
        System.out.println("");
        for (int current : new Primtall()) {
            if (current > 50) break;
            System.out.println(current);
        }
    }
}

class Primtall implements Iterator<Integer>, Iterable<Integer> {
    private int n = 2;

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        while (!erPrimtall(n)) {
            n++;
        }
        return n++;
    }

    private boolean erPrimtall(int n) {
        for (int i = 2; i*i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    @Override
    public Iterator<Integer> iterator() {
        return this;
    }
}







//class Range implements Iterator<Integer> {

//}