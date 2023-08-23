package no.oslomet.cs.algdat;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class EnhetstestingMinTest {

    @Test
    void minTomTabell() {
        int[] tom = {};
        assertThrows(NoSuchElementException.class, () -> EnhetstestingMin.min(tom));
    }

    @Test
    void minEttElement() {
        int[] a = {5};
        assertDoesNotThrow(() -> EnhetstestingMin.min(a), "Fikk feilmelding på tabell med ett element.");
        assertEquals(0, EnhetstestingMin.min(a), "Fikk ikke null. Forventet null.");
    }

    @Test
    void minTabellEndresIkke() {
        int[] a = {1, 4, 7, 4, 1, 5, 2};
        int[] b = a.clone();
        EnhetstestingMin.min(a);
        assertArrayEquals(b, a, "Dette endret på tabellen.");
    }
}