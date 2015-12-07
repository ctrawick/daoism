package net.cultured.daoism;

import java.util.function.Function;

/**
 * Creates a single data record where the key is auto-generated.
 *
 * @author chris
 *
 * @param <K>
 *            the key type
 * @param <T>
 *            the data type
 */
public interface GeneratedKeySingleCreator<K, T> extends Function<T, K> {

    /**
     * Create a single data record.
     *
     * @param data
     *            the data record to create
     * @return the generated key
     */
    K createOne(T data);

    /**
     * Access {@link #createOne(Object)} as a functional interface method.
     */
    @Override
    default K apply(final T t) {
        return createOne(t);
    }
}
