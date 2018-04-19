package net.kasterma.pgm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class PlainFactor implements Factor {
    private final double[] vals;
    private final Scope scope;

    public PlainFactor(double[] vals, Scope scope) {
        this.vals = vals;
        this.scope = scope;
    }

    /**
     * @return clone of the vals array.
     */
    public double[] getVals() {
        return vals.clone();
    }



    public Factor mul(PlainFactor other) {
//        Map<Integer, Integer> commonMapping = new HashMap<>();
//        IntStream.range(0, scope.size()).forEach(i -> {
//            int idx = other.scope.indexOf(scope.get(i));
//            if (idx >= 0) commonMapping.put(i, idx);
//        });
        return null;
    }

    @Override
    public Factor mul(Factor other) {
        return null;
    }
}
