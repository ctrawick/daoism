package net.cultured.daoism.spring.jdbc;

import java.util.List;
import java.util.function.BiFunction;

public interface BatchUpdateValidator<T> extends BiFunction<List<Integer>, List<T>, Boolean> {
    public Boolean validateBatchUpdate(List<Integer> updateCounts, List<T> data);

    @Override
    default Boolean apply(final List<Integer> t, final List<T> u) {
        return validateBatchUpdate(t, u);
    }
}
