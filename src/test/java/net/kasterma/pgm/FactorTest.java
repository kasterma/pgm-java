package net.kasterma.pgm;

import org.junit.jupiter.api.Test;
import org.nd4j.linalg.factory.Nd4j;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class FactorTest {
    @Test
    public void scopeInOut() {
        List<Integer> li = new LinkedList<>();
        li.add(0);
        li.add(1);
        Factor f1 = new Factor(Nd4j.create(2), li);
        assertTrue(f1.getScope() instanceof LinkedList);
    }

}