package no.oslomet.cs.algdat;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LivekodingTest {

    @Test
    void minTomTabell() {
        int[] tom = {};
        assertThrows(NoSuchElementException.class, () -> Livekoding.min(tom));
    }

    @Test
    void minEttElement() {
        int[] a = {5};
        assertDoesNotThrow(() -> Livekoding.min(a), "Fikk feilmelding på tabell med ett element.");
        assertEquals(0, Livekoding.min(a), "Fikk ikke null. Forventet null.");
    }

    @Test
    void minTabellEndresIkke() {
        int[] a = {1, 4, 7, 4, 1, 5, 2};
        int[] b = a.clone();
        Livekoding.min(a);
        assertArrayEquals(b, a, "Dette endret på tabellen.");
    }
}