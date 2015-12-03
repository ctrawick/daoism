package net.cultured.daoism;

import java.util.function.BiConsumer;

public interface KeyedSingleUpdater<K, T> extends BiConsumer<K, T> {
    void updateOneByKey(K key, T data);

    @Override
    default void accept(final K t, final T u) {
        updateOneByKey(t, u);
    }
}
