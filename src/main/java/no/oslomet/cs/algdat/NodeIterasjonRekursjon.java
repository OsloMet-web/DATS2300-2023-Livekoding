package no.oslomet.cs.algdat;

public class NodeIterasjonRekursjon {
}

class EnkeltLenketListe<T> {
    private static class Node<T> {
        Node<T> neste;
        T verdi;

        public Node(T verdi) {
            this.verdi = verdi;
        }

        public Node(T verdi, Node<T> neste) {
            this.verdi = verdi; this.neste = neste;
        }

        public Node<T> hentNode(T verdi) {
            if (this.verdi.equals(verdi)) return this;
            if (neste == null) return null;
            return neste.hentNode(verdi);
        }

        public void leggInn(int indeks, T verdi) {
            if (indeks == 0) {
                Node<T> nyNode = new Node(verdi, neste);
                neste = nyNode;
            } else {
                leggInn(indeks-1, verdi);
            }
        }
    }

    private Node<T> hode;
    private Node<T> hale;
    private int antall = 0;

    public void leggInnStart(T verdi) {
        hode = new Node<>(verdi, hode);
        if (hale == null) hale = hode;
        antall++;
    }

    public int antall() {
        return antall;
    }

    private Node<T> hentNodeIterativ(T verdi) {
        //To måter:
        //Iterativ:
        Node<T> denne = hode;
        while (denne != null) {
            if (denne.verdi.equals(verdi))
                break;
            denne = denne.neste;
        }
        return denne;
    }

    private Node<T> hentNodeRekursiv(T verdi) {
        if (hode == null) return null;
        return hode.hentNode(verdi);
    }

    public void leggInn(int indeks, T verdi) {
        if (indeks < 0 || indeks > antall) throw new IndexOutOfBoundsException();
        if (indeks == 0) leggInnStart(verdi);
        hode.leggInn(indeks-1, verdi);
        if (hale.neste != null) {
            hale = hale.neste;
        }
    }
}
