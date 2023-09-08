package no.oslomet.cs.algdat;

import jdk.jshell.spi.ExecutionControl;

public class Binærtre {
    Node root;
    int length;

    public Binærtre() {
        this.root = null;
    }

    public void append(char c) {
        if (this.root == null) {
            this.root = new Node(c);
        } else {
           this.root.append(c);
        }
        this.length++;
    }

    public static Binærtre fromList(char[] c) {
        Binærtre bTre = new Binærtre();
        for (char ch : c) {
            bTre.append(ch);
        }
        return bTre;
    }

    public char[] toSortedList() {
        char[] sortedList = new char[this.length];
        // Idé: Be hver node om å legge inn sin verdi, og øke telleren
        // som holder styr på hvor langt vi har kommet. Gå først til venstre.
        //throw new ExecutionControl.NotImplementedException("Gadd ikke lage denne ferdig.");
        throw new UnsupportedOperationException("Gadd ikke lage denne ferdig.");
    }
}

class Node {
    Node leftChild;
    Node rightChild;
    char value;

    public Node(char c) {
        this.value = c;
        this.leftChild = null;
        this.rightChild = null;
    }

    public void append(char c) {
        if (c < this.value) {
            if (this.leftChild == null) {
                this.leftChild = new Node(c);
            } else {
                this.leftChild.append(c);
            }
        } else {
            if (this.rightChild == null) {
                this.rightChild = new Node(c);
            } else {
                this.rightChild.append(c);
            }
        }
    }
}
