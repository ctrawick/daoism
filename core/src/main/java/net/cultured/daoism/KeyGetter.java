package net.cultured.daoism;

import java.util.function.Function;

public interface KeyGetter<K, T> extends Function<T, K> {
    K getKey(T data);

    @Override
    default K apply(final T t) {
        return getKey(t);
    }
}
