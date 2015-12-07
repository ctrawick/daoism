package net.cultured.daoism;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
 * Creates batches of data where keys are auto-generated. Implementations of
 * this interface take advantage of underlying data store efficiencies to create
 * multiple records in a single step.
 *
 * @author chris
 *
 * @param <K>
 *            the key type
 * @param <T>
 *            the data type
 */
public interface GeneratedKeyBatchCreator<K, T> extends Function<Collection<T>, Map<T, K>> {

    /**
     * Create multiple data records.
     *
     * @param data
     *            the data records to create
     * @return a map of the input data to the key generated for each record
     */
    Map<T, K> createMany(Collection<T> data);

    /**
     * Access {@link #createMany(Collection)} as a functional interface method.
     */
    @Override
    default Map<T, K> apply(final Collection<T> t) {
        return createMany(t);
    }
}
