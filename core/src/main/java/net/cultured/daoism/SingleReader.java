package net.cultured.daoism;

import java.util.function.Function;

public interface SingleReader<K, T> extends Function<K, T> {
    T readOne(K key);

    @Override
    default T apply(final K t) {
        return readOne(t);
    }
}
