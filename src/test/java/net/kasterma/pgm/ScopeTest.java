package net.kasterma.pgm;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ScopeTest {
    @Test
    public void getStrides() {
        Scope s1 = Scope.builder()
                .addDomain(new Domain(0, 1))
                .addDomain(new Domain(1, 2))
                .addDomain(new Domain(2, 3))
                .build();
        List<Integer> strides = s1.getStrides();
        assertEquals(strides.size(), 3);
        assertEquals((int) strides.get(0), 1);
        assertEquals((int) strides.get(1), 2);
        assertEquals((int) strides.get(2), 6);

        Scope s2 = Scope.builder()
                .addDomain(new Domain(0, 2))
                .addDomain(new Domain(1, 2))
                .addDomain(new Domain(2, 3))
                .build();
        strides = s2.getStrides();
        assertEquals(strides.size(), 3);
        assertEquals((int) strides.get(0), 2);
        assertEquals((int) strides.get(1), 4);
        assertEquals((int) strides.get(2), 12);
    }

}