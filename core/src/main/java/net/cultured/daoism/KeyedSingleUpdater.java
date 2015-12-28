package net.cultured.daoism;

import java.util.function.BiConsumer;

/**
 * Updates a single record of data by key, useful for re-keying data.
 *
 * @author chris
 * @param <K>
 *            the key type
 * @param <T>
 *            the data type
 */
public interface KeyedSingleUpdater<K, T> extends BiConsumer<K, T> {
    /**
     * Update a single record by key.
     *
     * @param key
     *            the old key
     * @param data
     *            the data record, which may contain a new key
     */
    void updateOneByKey(K key, T data);

    /**
     * Access {@link #updateOneByKey(Object, Object)} as a functional interface
     * method.
     */
    @Override
    default void accept(final K t, final T u) {
        updateOneByKey(t, u);
    }
}
