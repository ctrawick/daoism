package net.cultured.daoism;

import java.util.Map;
import java.util.function.Consumer;

/**
 * Updates batches of data by key, useful for re-keying data. Implementations of
 * this interface take advantage of underlying data store efficiencies to update
 * multiple records in a single step.
 *
 * @author chris
 * @param <K>
 *            the key type
 * @param <T>
 *            the data type
 */
public interface KeyedBatchUpdater<K, T> extends Consumer<Map<K, T>> {
    /**
     * Update multiple data records by key. The map keys are used to identify
     * existing records, whose keys are then updated to those of the map values.
     *
     * @param data
     *            the data records to update
     */
    void updateManyByKey(Map<K, T> data);

    /**
     * Access {@link #updateManyByKey(Map)} as a functional interface method.
     */
    @Override
    default void accept(final Map<K, T> t) {
        updateManyByKey(t);
    }
}
