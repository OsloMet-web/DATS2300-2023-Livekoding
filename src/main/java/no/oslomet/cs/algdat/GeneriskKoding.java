package no.oslomet.cs.algdat;
import java.util.function.Predicate;
import java.util.*;
public class GeneriskKoding {
    public static void oldmain(String[] args) {
        Person nikolai = new Person("Nikolai", 34);
        Node<Person> n = new Node<Person>(nikolai);
        Node<String> s = new Node<String>("Nikolai");
        Node<Object> objNode = new Node<Object>("Nikolai");
        Student nikolaiSomStudent = new Student("Nikolai", 24, "Matematikk");
        Node<Person> nSNode = new Node<Person>(nikolaiSomStudent);
        n.left = nSNode;
        nSNode.left = n;

        // Ikke lov:
        // Node<int> i = new Node<int>(3);
        // Løsninga: *Pakker* vekk de primitive typene:
        Node<Integer> i = new Node<Integer>(3);
        //System.out.println(i.value);
        //maks(new Person[] {new Person("Nikolai Igjen", 34)});
        System.out.println(nikolai.compareTo(nikolaiSomStudent));
    }

    public static void main(String[] args) {
        Person testPerson1 = new Person("Test", 3);
        Person testPerson2 = new Person("Test", 7);
        Person testPerson3 = new Person("Test", 12);
        Student testStudent1 = new Student("Test", 21, "Datum");
        Student testStudent2 = new Student("Test", 22, "Data");
        Student testStudent3 = new Student("Test", 23, "Data");
        Person[] personListe = {testPerson2, testPerson3, testPerson1, testStudent1};
        Student[] studentListe = {testStudent1, testStudent3, testStudent2};
        System.out.println(maks(personListe));
        System.out.println(maks(studentListe));
        partisjoner(studentListe, (x) -> {return x.studieretning == "Data";});
        System.out.println(Arrays.toString(studentListe));
    }

    //public static <T extends Comparable<? super T>> int maks(T[] a) {
    //    int maksPlassering = 0;
    //    T maksVerdi = a[maksPlassering];
    //    for (int i = 1; i < a.length; ++i) {
    //        if (a[i].compareTo(maksVerdi) > 0) {
    //            maksPlassering = i;
    //            maksVerdi = a[i];
    //        }
    //    }
    //    return maksPlassering;
    //}

    public static <T> int maks(T[] a) {
        return maks(a, Sammenlikner.naturligOrden());
    }

    public static <T> int maks(T[] a, Sammenlikner s) {
        int maksPlassering = 0;
        T maksVerdi = a[maksPlassering];
        for (int i = 1; i < a.length; ++i) {
            if (s.sammenlikn(a[i], maksVerdi)) {
                maksPlassering = i;
                maksVerdi = a[i];
            }
        }
        return maksPlassering;
    }

    public static <T> int partisjoner(T[] a, Predicate<T> testpredikat) {
        int v = 0;
        int h = a.length-1;
        while (true) {
            while (v <= h && testpredikat.test(a[v])) ++v;
            while (v <= h && !testpredikat.test(a[h])) --h;
            if (v >= h) break;
            bytt(a, v++, h--);
        }
        return v;
    }

    public static <T> void bytt(T[] a, int i, int j) {
        T tmp = a[i];
        a[i] =a[j];
        a[j] = tmp;
    }
}

class Node<T> {
    Node<? extends T> left;
    Node<? extends T> right;
    T value;

    public Node(T v) {
        value = v;
        left = null;
        right = null;
    }
}

class Person implements Comparable<Person> {
    String navn;
    int alder;

    public Person(String n, int a) {
        navn = n; alder = a;
    }

    public int compareTo(Person p) {
        return alder - p.alder;
    }

    public String toString() {
        return navn + " på " + alder + " år";
    }
}

class Student extends Person {
    String studieretning;

    public Student(String n, int a, String studretn) {
        super(n, a);
        studieretning = studretn;
    }

    public String toString() {
        return super.toString() + " som studerer " + studieretning;
    }
}

interface Sammenliknbar<T> {
    int sammenliknMed(T o);
}

interface Sammenlikner<T> {
    boolean sammenlikn(T v, T h);

    static <T extends Comparable<? super T>> Sammenlikner<T> naturligOrden() {
        return (v, h) -> (v.compareTo(h) > 0);
    }

    static <T extends Comparable<? super T>> Sammenlikner<T> motsattOrden() {
        return (v, h) -> (v.compareTo(h) < 0);
    }

}