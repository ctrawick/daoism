package net.cultured.daoism;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

public interface GeneratedKeyBatchCreator<K, T> extends Function<Collection<T>, Map<T, K>> {
    Map<T, K> createMany(Collection<T> data);

    @Override
    default Map<T, K> apply(final Collection<T> t) {
        return createMany(t);
    }
}
