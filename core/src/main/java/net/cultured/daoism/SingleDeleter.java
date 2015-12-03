package net.cultured.daoism;

import java.util.function.Consumer;

public interface SingleDeleter<K> extends Consumer<K> {
    void deleteOne(K key);

    @Override
    default void accept(final K t) {
        deleteOne(t);
    }
}
