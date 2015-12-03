package net.cultured.daoism;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Consumer;

public class BatchDeleterAdapter<K> implements SingleDeleter<K> {

    private Consumer<Collection<K>> delegate;

    public Consumer<Collection<K>> getDelegate() {
        return this.delegate;
    }

    public void setDelegate(final Consumer<Collection<K>> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void deleteOne(final K key) {
        final Set<K> keys = Collections.singleton(key);
        this.delegate.accept(keys);
    }

}
