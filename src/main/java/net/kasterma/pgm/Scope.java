package net.kasterma.pgm;

import java.util.ArrayList;
import java.util.List;

/**
 * Scope of a Factor.
 *
 * A scope is the set of variables that is the domain of the factor.  To create
 * a scope use the builder:
 *
 *     Scope scope = Scope.builder()
 *           .addDomain(new Domain(0, 1))
 *           .addDomain(new Domain(1, 2))
 *           .addDomain(new Domain(2, 3))
 *           .build();
 */
public class Scope {
    final List<Domain> domains;

    Scope(List<Domain> domains) {
        this.domains = domains;
    }

    public static ScopeBuilder builder() {
        return new ScopeBuilder();
    }

    List<Integer> getStrides() {
        List<Integer> strides = new ArrayList<>();
        int prod = 1;
        for (Domain d: domains) {
            strides.add(prod);
            prod *= d.getSize();
        }
        return strides;
    }

    /**
     * Number of values needed in a factor that has this scope.
     *
     * @return size to reserve for the double[] val array.
     */
    int size() {
        return domains.stream()
                .map(Domain::getSize)
                .reduce((i, j) -> i*j)
                .orElse(0);
    }

    /**
     * Compute index into the value array.
     *
     * @param idxs the indices in the different domains in the scope.
     * @return the index into the double[] val array.
     */
    int index(final int[] idxs) {
        List<Integer> strides = getStrides();
        if (idxs.length != strides.size()) {
            throw new IllegalArgumentException("not the correct number of idxs");
        }
        int idx = 0;
        for (int i = 0; i < strides.size(); i++) {
            idx += strides.get(i) * idxs[i];
        }
        return idx;
    }

    /**
     * Compute the indices in the different domains.
     *
     * @param idx the index into the double[] val array.
     * @return indices into the different domains.
     */
    int[] indices(final int idx) {
        if (idx >= size()) {
            throw new IllegalArgumentException("idx larger that scope allows");
        }
        List<Integer> strides = getStrides();
        int[] idxs = new int[domains.size()];
        int j = idx;
        for (int i = strides.size() - 1; i >=0; i--) {
            int stride = strides.get(i);
            idxs[i] = j / stride;
            j = j % stride;
        }
        return idxs;
    }
}

class ScopeBuilder extends Scope {
    ScopeBuilder() {
        super(new ArrayList<>());
    }

    ScopeBuilder addDomain(Domain d) {
        domains.add(d);
        return this;
    }

    ScopeBuilder addDomain(int id, int size) {
        domains.add(new Domain(id, size));
        return this;
    }

    Scope build() {
        return this;
    }
}
