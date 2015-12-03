package net.cultured.daoism;

import java.util.Collection;
import java.util.function.Consumer;

public interface BatchDeleter<K> extends Consumer<Collection<K>> {
    void deleteMany(Collection<K> keys);

    @Override
    default void accept(final Collection<K> t) {
        deleteMany(t);
    }
}
