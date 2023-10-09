package no.oslomet.cs.algdat;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Consumer;

public class Binærtre<T> {

    public static void main(String[] args) {
        Binærtre<String> binTre = new Binærtre<>();
        binTre.leggInn(1, "Jeg har indeks 1");
        binTre.leggInn(2, "Jeg har indeks 2");
        binTre.leggInn(5, "Jeg har indeks 5");
        binTre.leggInn(10, "Jeg har indeks 10");
        binTre.leggInn(3, "Jeg har indeks 3");
        binTre.leggInn(7, "Jeg har indeks 7");
        //binTre.leggInn(172, "Krasj");

        //binTre.nivåtraversering(System.out::println);
        binTre.dybdetraversering(System.out::println);
    }

    private class Node {
        Node venstre;
        Node høyre;
        T verdi;

        public Node(T verdi) {
            this.verdi = verdi; venstre = null; høyre = null;
        }

        public Node(T verdi, Node venstre, Node høyre) {
            this.verdi = verdi; this.venstre = venstre; this.høyre = høyre;
        }
    }

    Node rot;
    private int antall;

    public Binærtre() {
        rot = null; antall = 0;
    }

    public int antall() {return antall;}
    public boolean tom() {return antall == 0;}

    public void leggInn(int indeks, T verdi) {
        // Idé: Legg inn verdien på en gitt indeks
        if (indeks < 1) throw new IllegalArgumentException("Ikke lov med ikkepositive indekser.");
        if (rot == null) {
            if (indeks == 1) {
                rot = new Node(verdi);
                antall++;
                return;
            }
            else throw new IndexOutOfBoundsException("Prøver legge inn i et tomt tre på feil plass.");
        }
        char[] binærverdi = Integer.toBinaryString(indeks).toCharArray();
        Node currentNode = rot;
        for(int i = 1; i < binærverdi.length-1; ++i) {
            if (binærverdi[i] == '0') currentNode = currentNode.venstre;
            else currentNode = currentNode.høyre;
            if (currentNode == null) throw new IndexOutOfBoundsException("Prøver legge inn i ulovlig sted.");
        }
        if (binærverdi[binærverdi.length-1] == '0') {
            currentNode.venstre = new Node(verdi);
        } else {
            currentNode.høyre = new Node(verdi);
        }
        antall++;
    }

    public void nivåtraversering(Consumer<T> konsument) {
        Deque<Node> kø = new ArrayDeque<>();
        kø.addLast(rot);
        while(!kø.isEmpty()) {
            Node current = kø.removeFirst();
            if (current.venstre != null) kø.addLast(current.venstre);
            if (current.høyre != null) kø.addLast(current.høyre);
            konsument.accept(current.verdi);
        }
    }

    public void dybdetraversering(Consumer<T> konsument) {
        dybdetraversering(rot, konsument);
    }

    public void dybdetraversering(Node node, Consumer<T> konsument) {
        if (node == null) return;
        dybdetraversering(node.venstre, konsument);
        dybdetraversering(node.høyre, konsument);
        konsument.accept(node.verdi);
    }
}
