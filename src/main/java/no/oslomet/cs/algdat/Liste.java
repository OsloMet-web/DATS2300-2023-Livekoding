package no.oslomet.cs.algdat;

import java.util.Iterator;

public interface Liste<T> extends Beholder<T> {
    boolean leggInn(T verdi);            // Nytt element bakerst
    void leggInn(int indeks, T verdi);   // Nytt element på plass indeks
    boolean inneholder(T verdi);         // Sjekker om verdi er i listen
    T hent(int indeks);                  // Hent element på plass indeks
    int indeksTil(T verdi);              // Finn indeksen til verdi
    T oppdater(int indeks, T nyverdi);     // Oppdater verdi på plass indeks
    boolean fjern(T verdi);              // Fjerner objektet verdi
    T fjern(int indeks);                 // Fjern elementet på plass indeks, returner verdien
    int antall();                        // Antallet i listen
    boolean tom();                       // Sjekker om listen er tom
    void nullstill();                    // Listen tømmes
    Iterator<T> iterator();              // Returnerer iterator

    default String melding(int indeks) {
        return "Indeks: " + indeks + ", Antall: " + antall();
    }

    default void indeksKontroll(int indeks, boolean leggInn) {
        if (indeks < 0 || (leggInn ? indeks > antall() : indeks >= antall())) {
            throw new IndexOutOfBoundsException(melding(indeks));
        }
    }
}
