package net.cultured.daoism;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class BatchReaderAdapter<K, T> implements SingleReader<K, T> {

    private Function<Collection<K>, Map<K, T>> delegate;

    public Function<Collection<K>, Map<K, T>> getDelegate() {
        return this.delegate;
    }

    public void setDelegate(final Function<Collection<K>, Map<K, T>> delegate) {
        this.delegate = delegate;
    }

    @Override
    public T readOne(final K key) {
        final Set<K> keys = Collections.singleton(key);
        final Map<K, T> retMap = this.delegate.apply(keys);
        return retMap.get(key);
    }

}
