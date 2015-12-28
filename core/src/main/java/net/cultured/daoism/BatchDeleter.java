package net.cultured.daoism;

import java.util.Collection;
import java.util.function.Consumer;

/**
 * Deletes batches of data. Implementations of this interface take advantage of
 * underlying data store efficiencies to delete multiple records in a single
 * step.
 *
 * @author ctrawick
 *
 * @param <K>
 *            the key type
 */
public interface BatchDeleter<K> extends Consumer<Collection<K>> {
    /**
     * Delete multiple data records.
     *
     * @param keys
     *            the data keys to delete
     */
    void deleteMany(Collection<K> keys);

    /**
     * Access {@link #deleteMany(Collection)} as a functional interface method.
     */
    @Override
    default void accept(final Collection<K> t) {
        deleteMany(t);
    }
}
