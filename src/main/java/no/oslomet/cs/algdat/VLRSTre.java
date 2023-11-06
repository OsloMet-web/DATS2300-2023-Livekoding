package no.oslomet.cs.algdat;

import java.util.*;

public class VLRSTre<T> implements Beholder<T>{
    // Venstre-lent Rød-Sort-tre
    // Relevante metoder: leggInn, fargeskift, venstreroter, høyreroter, erRød
    private static final boolean RØD = true;
    private static final boolean SORT = false;

    private Node rot;
    int antall;

    Comparator<? super T> sammenlikner;

    private final class Node {
        Node venstre;
        Node høyre;
        T verdi;
        boolean farge;

        public Node(T verdi, Node v, Node h, boolean f) {
            venstre = v; høyre = h;
            this.verdi = verdi;
            farge = f;
        }

        public Node(T verdi) {
            this(verdi, null, null, RØD);
        }
    }

    public VLRSTre(Comparator<? super T> sammenlikner) {
        rot = null;
        antall = 0;
        this.sammenlikner = sammenlikner;
    }

    public int antall() {
        return antall;
    }

    public boolean tom() {
        return (antall == 0);
    }

    public boolean inneholder(T verdi) {
        Node h = rot;

        while (h != null) {
            int cmp = sammenlikner.compare(verdi, h.verdi);
            if (cmp == 0) return true;
            else if (cmp < 0) h = h.venstre;
            else h = h.høyre;
        }
        return false;
    }

    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi);
        int forrigeAntall = antall;
        rot = leggInn(rot, verdi);
        rot.farge = SORT; // Rota kan ende opp som rød til slutt.
        return (forrigeAntall != antall); // Sjekker om antall faktisk øker
    }

    private Node leggInn(Node h, T verdi) {
        if (h == null) {
            antall++;
            return new Node(verdi);
        }

        if (erRød(h.venstre) && erRød(h.høyre))
            fargeskift(h);

        int cmp = sammenlikner.compare(verdi, h.verdi);
        if (cmp == 0) return h;
        else if (cmp < 0) h.venstre = leggInn(h.venstre, verdi);
        else h.høyre = leggInn(h.høyre, verdi);

        if (!erRød(h.venstre) && erRød(h.høyre)) h = venstreroter(h);
        if (erRød(h.venstre) && erRød(h.venstre.venstre)) h = høyreroter(h);

        return h;
    }

    private void fargeskift(Node n) {
        n.farge = !n.farge;
        n.venstre.farge = !n.venstre.farge;
        n.høyre.farge = !n.høyre.farge;
    }

    private Node venstreroter(Node p) {
        Node q = p.høyre;
        p.høyre = q.venstre;
        q.venstre = p;
        q.farge = p.farge; p.farge = RØD;
        return q;
    }

    private Node høyreroter(Node p) {
        Node q = p.venstre;
        p.venstre = q.høyre;
        q.høyre = p;
        q.farge = p.farge; p.farge = RØD;
        return q;
    }

    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    public void nullstill() {
        throw new UnsupportedOperationException();
        /*
        Kunne bare satt rot = null og antall = 0
        men da kunne det vært at resten av treet ble i minnet
        så bedre å informere bruker om at de likegjerne selv
        kan lage nytt tre.
        */
    }

    private boolean erRød(Node p) {
        if (p == null) return false;
        return p.farge;
    }

    public Iterator<T> iterator() {
        return new BinTreIterator();
    }

    private final class BinTreIterator implements Iterator<T> {
        Deque<Node> stack;
        Node current;

        public BinTreIterator() {
            stack = new ArrayDeque<>();
            current = rot;
            while (current.venstre != null) {
                stack.addFirst(current);
                current = current.venstre;
            }
        }

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            T returverdi = current.verdi;
            if (current.høyre == null)
                current = stack.isEmpty() ? null : stack.removeFirst();
            else {
                current = current.høyre;
                while (current.venstre != null) {
                    stack.addFirst(current);
                    current = current.venstre;
                }
            }
            return returverdi;
        }
    }
}
