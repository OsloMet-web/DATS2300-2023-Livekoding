package no.oslomet.cs.algdat;

public class KarakterBinærtre {
    KarakterNode root;
    int length;

    public static void main(String[] args) {
        char[] cList = {'e', 'a', 'o', 'v'};
        KarakterBinærtre btre = KarakterBinærtre.fromList(cList);
        System.out.println(btre.root.rightChild.value);
    }

    public KarakterBinærtre() {
        this.root = null;
    }

    public void append(char c) {
        if (this.root == null) {
            this.root = new KarakterNode(c);
        } else {
           this.root.append(c);
        }
        this.length++;
    }

    public static KarakterBinærtre fromList(char[] c) {
        KarakterBinærtre bTre = new KarakterBinærtre();
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

class KarakterNode {
    KarakterNode leftChild;
    KarakterNode rightChild;
    char value;

    public KarakterNode(char c) {
        value = c;
        leftChild = null;
        rightChild = null;
    }

    public void append(char c) {
        if (c < value) {
            if (leftChild == null) {
                leftChild = new KarakterNode(c);
            } else {
                leftChild.append(c);
            }
        } else {
            if (rightChild == null) {
                rightChild = new KarakterNode(c);
            } else {
                rightChild.append(c);
            }
        }
    }
}
