package net.cultured.daoism;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * Exposes a {@link GeneratedKeyBatchCreator} as a
 * {@link GeneratedKeySingleCreator}.
 *
 * @author ctrawick
 *
 * @param <K>
 *            the key type
 * @param <T>
 *            the data type
 */
public class GeneratedKeySingleCreatorAdapter<K, T> implements GeneratedKeySingleCreator<K, T> {

    private Function<Collection<T>, Map<T, K>> delegate;

    /**
     * Get the batch creator delegate.
     *
     * @return a delegate
     * @see #setDelegate(Function)
     */
    public Function<Collection<T>, Map<T, K>> getDelegate() {
        return this.delegate;
    }

    /**
     * Set the batch creator delegate. This delegate will be used to create data
     * records one at a time.
     *
     * @param delegate
     *            a delegate
     */
    public void setDelegate(final Function<Collection<T>, Map<T, K>> delegate) {
        this.delegate = delegate;
    }

    /**
     * Create a single data record. This implementation will wrap the data in a
     * singleton collection and use the batch creator delegate to create the
     * record.
     *
     * @throws NullPointerException
     *             if delegate is null
     */
    @Override
    public K createOne(final T data) {
        final Set<T> datas = Collections.singleton(data);
        final Map<T, K> retMap = this.delegate.apply(datas);
        return retMap.get(data);
    }

}
