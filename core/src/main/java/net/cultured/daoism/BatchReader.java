package net.cultured.daoism;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
 * Reads batches of data. Implementations of this interface take advantage of
 * underlying data store efficiencies to read multiple records in a single step.
 *
 * @author ctrawick
 *
 * @param <K>
 *            the key type
 * @param <T>
 *            the data type
 */
public interface BatchReader<K, T> extends Function<Collection<K>, Map<K, T>> {

    /**
     * Read a batch of records.
     *
     * @param keys
     *            the keys of the records to read.
     * @return a map of each key to the associated data
     */
    Map<K, T> readMany(Collection<K> keys);

    /**
     * Access {@link #readMany(Collection)} as a functional interface method.
     */
    @Override
    default Map<K, T> apply(final Collection<K> t) {
        return readMany(t);
    }
}
