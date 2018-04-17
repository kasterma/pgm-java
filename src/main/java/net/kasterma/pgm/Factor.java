package net.kasterma.pgm;

import lombok.extern.log4j.Log4j2;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Factor.
 *
 * TODO: make ready for extensions to e.g. probabilities.
 */
@Log4j2
public final class Factor {
    private final INDArray vals;
    private final List<Integer> scope;

    public Factor(final INDArray vals, final List<Integer> scope) {
        this.vals = vals;
        this.scope = scope;
    }

    /**
     * Product of factors.
     */
    Factor mul(Factor other) {
        Map<Integer, Integer> commonMapping = new HashMap<>();
        IntStream.range(0, scope.size()).forEach(i -> {
            int idx = other.scope.indexOf(scope.get(i));
            if (idx >= 0) commonMapping.put(i, idx);
        });

        return null;  // TODO: create actual multiplication
    }

    /**
     * Get a copy of the scope.
     * @return
     */
    List<Integer> getScope() {
        try {
            @SuppressWarnings("unchecked")
            List<Integer> l = scope.getClass().newInstance();
            l.addAll(scope);
            return l;
        } catch (InstantiationException e) {
            log.info("Error instantiating new version of scope: {}",
                    e.toString());
        } catch (IllegalAccessException e) {
            log.info("Illegal to create new version of scope: {}",
                    e.toString());
        }
        return new ArrayList<>(scope);
    }


    public static void main( String[] args ) {
        INDArray a1 = Nd4j.ones(2, 2);
        INDArray a2 = Nd4j.ones(2, 3);
        a2 = a2.mul(2);
        System.out.println(a2.toString());
        System.out.println(a1.mmul(a2).toString());
        double[][] xs = {{1, 2, 3}, {4, 5, 6}};
        INDArray a3 = Nd4j.create(xs);

        double[][] as = {{1, 2}, {3, 4}};
        INDArray a4 = Nd4j.create(as);
        System.out.println(a3.toString());
        System.out.println(a4.mmul(a4).toString());
        System.out.println(a4.mul(a4).toString());
        int[] sh = a4.shape();
        System.out.println("[" + Arrays.stream(sh).mapToObj(Integer::toString).collect(Collectors.joining(", ")) + "]");
    }
}
