package net.cultured.daoism;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Exposes a {@link BatchDeleter} as a {@link SingleDeleter}.
 *
 * @author chris
 *
 * @param <K>
 *            the key type
 */
public class BatchDeleterAdapter<K> implements SingleDeleter<K> {

    private Consumer<Collection<K>> delegate;

    /**
     * Get the batch deleter delegate.
     *
     * @return a delegate
     * @see #setDelegate(Consumer)
     */
    public Consumer<Collection<K>> getDelegate() {
        return this.delegate;
    }

    /**
     * Set the batch deleter delegate. This delegate will be used to delete data
     * records one at a time.
     *
     * @param delegate
     *            a delegate
     */
    public void setDelegate(final Consumer<Collection<K>> delegate) {
        this.delegate = delegate;
    }

    /**
     * Delete a single data record. This implementation will wrap the key in a
     * singleton collection and use the batch deleter delegate to delete the
     * record.
     *
     * @throws NullPointerException
     *             if delegate is null
     */
    @Override
    public void deleteOne(final K key) {
        final Set<K> keys = Collections.singleton(key);
        this.delegate.accept(keys);
    }

}
