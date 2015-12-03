package net.cultured.daoism;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

public class KeyedSingleUpdaterAdapter<K, T> implements KeyedSingleUpdater<K, T> {

    private Consumer<Map<K, T>> delegate;

    public Consumer<Map<K, T>> getDelegate() {
        return this.delegate;
    }

    public void setDelegate(final Consumer<Map<K, T>> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void updateOneByKey(final K key, final T data) {
        final Map<K, T> dataMap = Collections.singletonMap(key, data);
        this.delegate.accept(dataMap);
    }

}
