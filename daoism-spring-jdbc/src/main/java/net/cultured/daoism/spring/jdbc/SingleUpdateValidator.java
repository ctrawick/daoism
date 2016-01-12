package net.cultured.daoism.spring.jdbc;

import java.util.function.BiFunction;

public interface SingleUpdateValidator<T> extends BiFunction<Integer, T, Boolean> {
    public Boolean validateSingleUpdate(int updateCount, T data);

    @Override
    default Boolean apply(final Integer t, final T u) {
        return validateSingleUpdate(t, u);
    }
}
