package net.kasterma.pgm;

import io.vavr.control.Either;

public class PlainFactor implements Factor {
    private final double[] vals;
    private final Scope scope;

    public PlainFactor(double[] vals, Scope scope) {
        this.vals = vals;
        this.scope = scope;
    }

    public static PlainFactorBuilder builder() {
        return new PlainFactorBuilder();
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

class PlainFactorBuilder {
    private double[] vals;
    private Either<Scope, ScopeBuilder> scope;

    PlainFactorBuilder() {
        vals = null;
        scope = Either.right(new ScopeBuilder());
    }

    PlainFactorBuilder addDomain(Domain d) {
        if (scope.isLeft()) {
            throw new IllegalArgumentException("Adding domain but scope is already build");
        }

        scope.get().addDomain(d);
        return this;
    }

    PlainFactorBuilder addDomain(int id, int size) {
        if (scope.isLeft()) {
            throw new IllegalArgumentException("Adding domain but scope is already build");
        }

        scope.get().addDomain(id, size);
        return this;
    }

    PlainFactorBuilder setValues(double[] vals) {
        if (scope.isRight()) {
            scope = Either.left(scope.get().build());
        }
        this.vals = vals;
        return this;
    }
}