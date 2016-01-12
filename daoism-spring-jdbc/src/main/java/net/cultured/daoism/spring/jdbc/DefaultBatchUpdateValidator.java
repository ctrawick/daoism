package net.cultured.daoism.spring.jdbc;

import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;

public class DefaultBatchUpdateValidator<T> implements BatchUpdateValidator<T> {
    private BiFunction<Integer, T, Boolean> delegate = new DefaultSingleUpdateValidator<>();

    public BiFunction<Integer, T, Boolean> getDelegate() {
        return this.delegate;
    }

    public void setDelegate(final BiFunction<Integer, T, Boolean> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Boolean validateBatchUpdate(final List<Integer> updateCounts, final List<T> batch) {
        final boolean ret = true;
        final Iterator<Integer> countIterator = updateCounts.iterator();
        final Iterator<T> dataIterator = batch.iterator();
        while (countIterator.hasNext()) {
            final int updateCount = countIterator.next();
            final T data = dataIterator.next();
            this.delegate.apply(updateCount, data);
        }
        return ret;
    }

}
