package net.kasterma.pgm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Scope {
    protected final List<Domain> domains;

    Scope(List<Domain> domains) {
        this.domains = domains;
    }

    static ScopeBuilder builder() {
        return new ScopeBuilder();
    }

    List<Integer> getStrides() {
        List<Integer> domainSizes =
                domains.stream().map(Domain::getSize)
                        .collect(Collectors.toList());
        List<Integer> strides = new ArrayList<>();
        strides.add(domainSizes.get(0));
        domainSizes.stream().reduce((i, j) -> {int rv = i*j; strides.add(rv); return rv;});
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

    Scope build() {
        return this;
    }
}
