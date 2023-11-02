package no.oslomet.cs.algdat;

import java.util.Arrays;
import java.util.Objects;

public class Hashing {
    public static void main(String[] args) {
        String i = "Dette er en tekst";
        System.out.println(i.hashCode() +" = "+ hash(i));

        Teit o = new Teit(3, "Hei");
        System.out.println(o.hashCode());
        Teit p = new Teit(3, "Hei");
        System.out.println(o.equals(p));

        System.out.println(i.equals(new String("Dette er en tekst")));

        Integer k = 3;
        Integer l = new Integer(3);
        System.out.println(k == l);

        // Hva Objects.hash gjør.
        Object[] oArr = new Object[] {3, "Hei"};
        System.out.println(oArr.hashCode());
        System.out.println(Arrays.hashCode(oArr));
        System.out.println(p.hashCode());

        System.out.println(8768 % 29);
    }

    public static int hash(String s) {
        char[] c = s.toCharArray();
        int hash = 0;
        for (int i = 0; i < c.length; i++) {
            hash = 31*hash + (int) c[i];
        }
        return hash;
    }
}

class Teit {
    int i;
    String t;

    public Teit(int i, String t) {
        this.i = i; this.t = t;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teit teit = (Teit) o;
        return i == teit.i && Objects.equals(t, teit.t);
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, t);
    }
}

class PersonMedFnr {
    private String fornavn;
    private String etternavn;
    private int fødselsnummer;

    public PersonMedFnr(String fn, String en, int føn) {
        fornavn = fn; etternavn = en; fødselsnummer = føn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonMedFnr that = (PersonMedFnr) o;
        return fødselsnummer == that.fødselsnummer;
    }

    @Override
    public int hashCode() {
        return fødselsnummer+1;
    }
}
