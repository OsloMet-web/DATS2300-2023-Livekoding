package no.oslomet.cs.algdat;
import java.util.*;

public class KarakterBinærtre {
    KarakterNode root;
    int length;

    public static void main(String[] args) {
        char[] cList = {'e', 'a', 'o', 'v', 'w', 'A', 'æ', 'ø', 'å'};
        KarakterBinærtre btre = KarakterBinærtre.fromList(cList);
        System.out.println(Arrays.toString(btre.toSortedList()));
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
        root.toSortedList(sortedList, 0);
        return sortedList;
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

    public int toSortedList(char[] sortedList, int i) {
        int counter = i;
        if (leftChild != null) {
            counter = leftChild.toSortedList(sortedList, counter);
        }
        sortedList[counter++] = value;
        if(rightChild != null) {
            counter = rightChild.toSortedList(sortedList, counter);
        }
        return counter;
    }
}
