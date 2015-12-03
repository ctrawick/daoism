package net.cultured.daoism;

import java.util.Map;
import java.util.function.Consumer;

public interface KeyedBatchUpdater<K, T> extends Consumer<Map<K, T>> {
    void updateManyByKey(Map<K, T> data);

    @Override
    default void accept(final Map<K, T> t) {
        updateManyByKey(t);
    }
}
