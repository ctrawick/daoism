package net.cultured.daoism;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;

/**
 * Exposes a {@link BatchUpdater} as a {@link SingleUpdater}.
 *
 * @author ctrawick
 *
 * @param <T>
 *            the data type
 */
public class BatchUpdaterAdapter<T> implements SingleUpdater<T> {

    private Consumer<Collection<T>> delegate;

    /**
     * Get the batch updater delegate.
     *
     * @return a delegate
     * @see #setDelegate(Consumer)
     */
    public Consumer<Collection<T>> getDelegate() {
        return this.delegate;
    }

    /**
     * Set the batch updater delegate. This delegate will be used to update data
     * records one at a time.
     *
     * @param delegate
     *            a delegate
     */
    public void setDelegate(final Consumer<Collection<T>> delegate) {
        this.delegate = delegate;
    }

    /**
     * Update a single data record. This implementation will wrap the data in a
     * singleton collection and use the batch updater delegate to updater the
     * record.
     *
     * @throws NullPointerException
     *             if delegate is null
     */
    @Override
    public void updateOne(final T data) {
        this.delegate.accept(Collections.singleton(data));
    }

}
