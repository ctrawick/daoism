package net.cultured.daoism;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Exposes a {@link BatchCreator} as a {@link SingleCreator}.
 *
 * @author chris
 *
 * @param <T>
 *            the data type
 */
public class BatchCreatorAdapter<T> implements SingleCreator<T> {

    private Consumer<Collection<T>> delegate;

    /**
     * Get the batch creator delegate.
     *
     * @return a delegate
     * @see #setDelegate(Consumer)
     */
    public Consumer<Collection<T>> getDelegate() {
        return this.delegate;
    }

    /**
     * Set the batch creator delegate. This delegate will be used to create data
     * records one at a time.
     *
     * @param delegate
     *            a delegate
     */
    public void setDelegate(final Consumer<Collection<T>> delegate) {
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
    public void createOne(final T data) {
        final Set<T> datas = Collections.singleton(data);
        this.delegate.accept(datas);
    }

}
