package net.cultured.daoism;

import java.util.function.BiConsumer;

public interface KeySetter<K, T> extends BiConsumer<T, K> {
    void setKey(T data, K key);

    @Override
    default void accept(final T t, final K u) {
        setKey(t, u);
    }
}
