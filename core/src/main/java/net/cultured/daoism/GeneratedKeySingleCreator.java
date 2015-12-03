package net.cultured.daoism;

import java.util.function.Function;

public interface GeneratedKeySingleCreator<K, T> extends Function<T, K> {
    K createOne(T data);

    @Override
    default K apply(final T t) {
        return createOne(t);
    }
}
