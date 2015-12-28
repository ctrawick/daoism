package net.cultured.daoism;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * Exposes a {@link BatchReader} as a {@link SingleReader}.
 *
 * @author ctrawick
 *
 * @param <K>
 *            the key type
 * @param <T>
 *            the data type
 */
public class BatchReaderAdapter<K, T> implements SingleReader<K, T> {

    private Function<Collection<K>, Map<K, T>> delegate;

    /**
     * Get the batch reader delegate.
     *
     * @return a delegate
     * @see #setDelegate(Function)
     */
    public Function<Collection<K>, Map<K, T>> getDelegate() {
        return this.delegate;
    }

    /**
     * Set the batch deleter delegate. This delegate will be used to read data
     * records one at a time.
     *
     * @param delegate
     *            a delegate
     */
    public void setDelegate(final Function<Collection<K>, Map<K, T>> delegate) {
        this.delegate = delegate;
    }

    /**
     * Read a single data record. This implementation will wrap the key in a
     * singleton collection and use the batch reader delegate to read the
     * record.
     *
     * @throws NullPointerException
     *             if delegate is null
     */
    @Override
    public T readOne(final K key) {
        final Set<K> keys = Collections.singleton(key);
        final Map<K, T> retMap = this.delegate.apply(keys);
        return retMap.get(key);
    }

}
