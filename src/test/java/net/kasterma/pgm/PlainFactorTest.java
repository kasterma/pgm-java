package net.kasterma.pgm;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Log4j2
public class PlainFactorTest {

    @Test
    public void getStrides() {
        Integer[] xs = {1, 2, 3};
        PlainFactor pf1 = new PlainFactor(null, Arrays.asList(xs));
        List<Integer> strides = pf1.getStrides();
        assertEquals(strides.size(), 3);
        assertEquals((int) strides.get(0), 1);
        assertEquals((int) strides.get(1), 2);
        assertEquals((int) strides.get(2), 6);

        Integer[] xs2 = {2, 2, 3};
        pf1 = new PlainFactor(null, Arrays.asList(xs2));
        strides = pf1.getStrides();
        assertEquals(strides.size(), 3);
        assertEquals((int) strides.get(0), 2);
        assertEquals((int) strides.get(1), 4);
        assertEquals((int) strides.get(2), 12);
    }
}