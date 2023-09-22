package no.oslomet.cs.algdat;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;

public interface Beholder<T> extends Iterable<T> {
    boolean leggInn(T t);    // Legger inn t i beholderen
    boolean inneholder(T t); // Sjekker om beholderen inneholder t
    boolean fjern(T t);      // Fjerner t fra beholderen
    int antall();            // Returnerer antall elementer i beholderen
    boolean tom();           // Sjekker om beholderen er tom
    void nullstill();        // Tømmer beholderen
    Iterator<T> iterator();  // Returnerer en iterator

    default boolean fjernHvis(Predicate<? super T> p) { // Betinget fjerning
        Objects.requireNonNull(p);  // Kaster unntak om p er null

        boolean fjernet = false;
        for(Iterator<T> i = iterator(); i.hasNext(); ) { // Itererer over elementer
            if (p.test(i.next())) { // Sjekker betingelsen
                i.remove(); // fjerner
                fjernet = true;
            }
        }
        return fjernet;
    }
}