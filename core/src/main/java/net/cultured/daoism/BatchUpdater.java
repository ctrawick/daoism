package net.cultured.daoism;

import java.util.Collection;
import java.util.function.Consumer;

/**
 * Updates batches of data. Implementations of this interface take advantage of
 * underlying data store efficiencies to update multiple records in a single
 * step.
 *
 * @author chris
 *
 * @param <T>
 *            the data type
 */
public interface BatchUpdater<T> extends Consumer<Collection<T>> {
    /**
     * Update multiple data records.
     *
     * @param data
     *            the data records to update
     */
    void updateMany(Collection<T> data);

    /**
     * Access {@link #updateMany(Collection)} as a functional interface method.
     */
    @Override
    default void accept(final Collection<T> t) {
        updateMany(t);
    }
}
