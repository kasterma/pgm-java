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
            prod *= d.getSize();
            strides.add(prod);
        }
        return strides;
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
