package no.oslomet.cs.algdat;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.Random;

public class BalanserendeTre {
    public static void main(String[] args) {
        RSTreBoka<Integer> rstreint = new RSTreBoka<>(Comparator.naturalOrder());
        int[] iList = {2, 14, 13, 0, 6, 5, 11, 12, 7, 10, 8, 9, 1, 3, 4};
        for (int i : iList) {
            rstreint.leggInnRekursiv(i);
        }

        int n = 1_000_000;
        iList = randPerm(n);
        long tic = System.currentTimeMillis();
        for (int i : iList) {
            rstreint.leggInnIterativ(i);
        }
        System.out.println(System.currentTimeMillis() - tic);
        RSTreBoka<Integer> rstreint2 = new RSTreBoka<>(Comparator.naturalOrder());
        tic = System.currentTimeMillis();
        for (int i : iList) {
            rstreint2.leggInnRekursiv(i);
        }
        System.out.println(System.currentTimeMillis()-tic);
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

    public static void bytt(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}

class RSTreBoka<T> {
    private static final boolean RØD = true;
    private static final boolean SORT = false;
    private final class Node {
        Node venstre;
        Node høyre;
        boolean farge;
        T verdi;

        Node(T verdi) {
            this.verdi = verdi;
            this.farge = RØD;
        }
    }

    Node rot;
    int antall;
    Comparator<? super T> sammenlikner;

    public RSTreBoka(Comparator<? super T> sammenlikner) {
        this.sammenlikner = sammenlikner; antall = 0;
    }

    public int antall() {
        return antall;
    }

    public boolean tom() {
        return antall == 0;
    }

    private boolean erRød(Node h) {
        return (h != null && h.farge);
    }

    private void fargeskift(Node h) {
        h.farge = !h.farge;
        h.venstre.farge = !h.venstre.farge;
        h.høyre.farge = !h.høyre.farge;
    }

    // Både venstreroterer og fargeskifter riktig
    private Node venstreRoter(Node h) {
        Node q = h.høyre;
        h.høyre = q.venstre;
        q.venstre = h;
        q.farge = h.farge;
        h.farge = RØD;
        return q;
    }

    // Både høyreroterer og fargeskifter riktig
    private Node høyreRoter(Node h) {
        Node q = h.venstre;
        h.venstre = q.høyre;
        q.høyre = h;
        q.farge = h.farge;
        h.farge = RØD;
        return q;
    }

    public boolean leggInnIterativ(T verdi) {

        // Legger inn rotnoden dersom treet er tomt.
        if (rot == null) {
            rot = new Node(verdi);
            rot.farge = SORT;
            antall++;
            return true;
        }

        // Går gjennom treet og finner riktig plassering for den nye noden
        // Lagrer verdiene vi går gjennom i en stabel, så vi kan gå baklengs når vi rydder opp
        Node p = rot;
        Deque<Node> stakk = new ArrayDeque<>();
        int cmp = 0;
        while (p != null) {
            stakk.addFirst(p);
            cmp = sammenlikner.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return false; // Ulovlig med duplikatverdier
        }
        p = new Node(verdi);
        Node f = stakk.removeFirst();
        // f er forelderen til p, p legges inn riktig plassering
        if (cmp < 0) f.venstre = p;
        else f.høyre = p;

        Node b;
        while (erRød(f) && !stakk.isEmpty()) {
            // Vi er nå i situasjonen hvor vi har rød forelder.
            b = stakk.removeFirst(); // besteforelderen til p
            if (erRød(b.venstre) && erRød(b.høyre)) { // vi legger inn i en 4-node
                // Vi utfører et fargeskift, og sjekker om b er lovlig plassert
                fargeskift(b);
                if (rot.equals(b)) break;
                p = b; f = stakk.removeFirst(); continue;
            }
            // Vi er nå i situasjonen hvor vi har lagt inn i en 3-node. Vi må finne ut av hvilken av de fire mulige situasjonene vi er i.
            if (f.equals(b.venstre)) {
                if (p.equals(f.høyre)) { // b-f-p ligger mot venstre, med knekk
                    b.venstre = venstreRoter(f); // Rotert bort knekk, ligger nå b-p-f på linje
                }
                p = høyreRoter(b);
            } else {
                if (p.equals(f.venstre)) { // b-f-p ligger mot høyre, med knekk
                    b.høyre = høyreRoter(f); // Rotert bort knekk, ligger nå b-p-f på linje
                }
                p = venstreRoter(b);
            }
            if (rot.equals(b)) {
                rot = p; break;
            }
            Node o = stakk.getFirst();
            if (b.equals(o.venstre)) o.venstre = p;
            else o.høyre = p;
            break;
        }

        antall++;
        rot.farge = SORT;
        return true;
    }

    public boolean leggInnRekursiv(T verdi) {
        int forrigeAntall = antall;
        rot = leggInnRekursiv(rot, verdi);
        rot.farge = SORT;
        return antall != forrigeAntall;
    }

    private Node leggInnRekursiv(Node h, T verdi) {
        if (h == null) {
            antall++;
            return new Node(verdi);
        }
        int cmp = sammenlikner.compare(verdi, h.verdi);
        if (cmp < 0) h.venstre = leggInnRekursiv(h.venstre, verdi);
        else if (cmp > 0) h.høyre = leggInnRekursiv(h.høyre, verdi);
        else return h;

        // Idéen nå er å gå baklengs og rette opp i feil etterhvert som vi ser dem.
        // Men vi vet ikke hvordan vi skal rette feilen før vi kommer til besteforelderen
        // Så denne algoritmen er skrevet fra synspunktet til besteforelderen

        if (erRød(h.venstre) && erRød(h.venstre.høyre)) { // en "knekk" mot venstre
            if (erRød(h.høyre)) { // legger inn i 4-node
                fargeskift(h);
            } else { // legger inn i 3-node
                h.venstre = venstreRoter(h.venstre);
            }
        } else if (erRød(h.høyre) && erRød(h.høyre.venstre)) { // en "knekk" mot høyre
            if (erRød(h.venstre)) { // legger inn i 4-node
                fargeskift(h);
            } else { // legger inn i 3-node
                h.høyre = høyreRoter(h.høyre);
            }
        }
        // Alle "knekker" er løst, men kan fremdeles ha streker av røde noder

        if (erRød(h.venstre) && erRød(h.venstre.venstre)) { // to røde til venstre på rad
            if (erRød(h.høyre)) { // legger inn i 4-node
                fargeskift(h);
            } else { // legger inn i 3-node
                h = høyreRoter(h);
            }
        } else if (erRød(h.høyre) && erRød(h.høyre.høyre)) { // to røde til høyre på rad
            if (erRød(h.venstre)) { // legger inn i 4-node
                fargeskift(h);
            } else { // legger inn i 3-node
                h = venstreRoter(h);
            }
        }
        return h;
    }
}