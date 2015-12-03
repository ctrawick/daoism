package net.cultured.daoism;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

public interface BatchReader<K, T> extends Function<Collection<K>, Map<K, T>> {
    Map<K, T> readMany(Collection<K> keys);

    @Override
    default Map<K, T> apply(final Collection<K> t) {
        return readMany(t);
    }
}
