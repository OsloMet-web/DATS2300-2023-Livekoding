package no.oslomet.cs.algdat;

import java.util.*;

public class ListeStrukturer {
    public static void main(String[] args) {
        char[] charArray = {'a', 'e', 'f', 'h', 'g', 'l'};
        UtvidbarListe uvl = new UtvidbarListe(charArray);
        //System.out.println(Arrays.toString(uvl.liste));
        for (char c : charArray) {
            uvl.add(c);
        }
        //System.out.println(Arrays.toString(uvl.liste));
        //System.out.println(uvl.length());
        //System.out.println(uvl.get(6));
        //System.out.println(uvl.get(11));
        uvl.add('j');
        //System.out.println(uvl.get(12));
        //System.out.println(Arrays.toString(uvl.liste));

        Integer[] intList = {2, 5, 3, 5, 7, 4};
        LenketListe<Integer> lliste = new LenketListe<Integer>(intList);
        //System.out.println(lliste.hentVerdi(3));

        for(LenketListe.Node i = lliste.hode; i != null; i = i.neste) {
            System.out.println(i.getVerdi());
        }
    }
}

class UtvidbarListe {
    char[] liste;
    int antall;

    public UtvidbarListe(char[] a) {
       liste = new char[2*a.length];
       antall = a.length;
       for (int i = 0; i < antall; ++i) liste[i] = a[i];
    }

    public char get(int i) {
        if (i >= antall || i < 0) throw new ArrayIndexOutOfBoundsException("For stor verdi");
        return liste[i];
    }

    public void add(char t) {
        if (antall < liste.length) {
            liste[antall++] = t;
        } else {
            char[] tmp = new char[2*liste.length];
            for (int i = 0; i < liste.length; ++i) {
                tmp[i] = liste[i];
            }
            liste = tmp;
            liste[antall++] = t;
        }
    }

    public void remove(int i) {
        for (int j = i; j < antall; j++) {
            liste[j] = liste[j+1];
        }
        antall--;
    }

    public int length() {
        return antall;
    }

}

class LenketListe<T>{
    class Node<T> {
        T verdi;
        Node<T> neste;

        public Node(T verdi) {
            this.verdi = verdi;
            this.neste = null;
        }

        public Node(T verdi, Node<T> neste) {
            this.verdi = verdi;
            this.neste = neste;
        }

        public T getVerdi() {
            return verdi;
        }
    }

    Node<T> hode;
    Node<T> hale;
    int antall;

    public LenketListe(T[] a) {
        hode = new Node(a[0]);
        Node<T> forrigeNode = hode;
        Node<T> nyNode = null;
        for (int i = 1; i < a.length; ++i) {
            nyNode = new Node(a[i]);
            forrigeNode.neste = nyNode;
            forrigeNode = nyNode;
        }
        hale = nyNode;
        antall = a.length;
    }

    public void leggTilStart(T t) {
        Node<T> nyNode = new Node(t);
        nyNode.neste = hode;
        hode = nyNode;
        antall++;
    }

    public void leggTilSlutt(T t) {
        Node<T> nyNode = new Node(t);
        hale.neste = nyNode;
        hale = nyNode;
        antall++;
    }

    public T hentVerdi(int plass) {
        if (plass >= antall || plass < 0) throw new ArrayIndexOutOfBoundsException();
        if (plass == antall - 1) return hale.getVerdi();
        Node<T> tmpNode = hode;
        for (int i = 0; i < plass; ++i) {
            tmpNode = tmpNode.neste;
        }
        return tmpNode.getVerdi();
    }

    public int size() {
        return antall;
    }
}
