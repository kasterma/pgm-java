package net.kasterma.pgm;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

@Log4j2
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
        assertEquals((int) strides.get(1), 1);
        assertEquals((int) strides.get(2), 2);

        Scope s2 = Scope.builder()
                .addDomain(0, 2)
                .addDomain(1, 2)
                .addDomain(2, 3)
                .build();
        strides = s2.getStrides();
        assertEquals(strides.size(), 3);
        assertEquals((int) strides.get(0), 1);
        assertEquals((int) strides.get(1), 2);
        assertEquals((int) strides.get(2), 4);
    }

    @Test
    public void indexOperations() {
        Scope s1 = Scope.builder()
                .addDomain(new Domain(0, 2))
                .addDomain(new Domain(1, 2))
                .addDomain(new Domain(2, 3))
                .build();

        for (int i = 0; i < s1.size(); i++) {
            String idxsStr = Arrays.stream(s1.indices(i))
                    .mapToObj(Integer::toString)
                    .collect(Collectors.joining(", "));
            log.info("idx {} idxs {}", i, idxsStr);
        }

        int[] idxs = {0, 0, 0};
        assertEquals(0, s1.index(idxs));
        idxs[0] = 1;
        assertEquals(1, s1.index(idxs));
        idxs[0] = 0;
        idxs[1] = 1;
        assertEquals(2, s1.index(idxs));
        idxs[1] = 0;
        idxs[2] = 2;
        assertEquals(8, s1.index(idxs));
        for (int i = 0; i < s1.size(); i++) {
            assertEquals(i, s1.index(s1.indices(i)));
        }
        int[] idxs_in = new int[3];
        IntStream.range(0, 2).forEach(i -> {
            IntStream.range(0, 2).forEach(j -> {
                IntStream.range(0, 3).forEach(k -> {
                    idxs_in[0] = i;
                    idxs_in[1] = j;
                    idxs_in[2] = k;
                    int[] idxs_out = s1.indices(s1.index(idxs_in));
                    assertEquals(i, idxs_out[0]);
                    assertEquals(j, idxs_out[1]);
                    assertEquals(k, idxs_out[2]);
                });
            });
        });
    }
}