package net.cultured.daoism;

import java.util.Collection;
import java.util.function.Consumer;

/**
 * Creates batches of data. Implementations of this interface take advantage of
 * underlying data store efficiencies to create multiple records in a single
 * step.
 *
 * @author chris
 *
 * @param <T>
 *            the data type
 */
public interface BatchCreator<T> extends Consumer<Collection<T>> {

    /**
     * Create multiple data records.
     *
     * @param data
     *            the data records to create
     */
    void createMany(Collection<T> data);

    /**
     * Access {@link #createMany(Collection)} as a functional interface method.
     */
    @Override
    default void accept(final Collection<T> t) {
        createMany(t);
    }
}
