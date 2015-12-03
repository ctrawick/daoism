package net.cultured.daoism;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class GeneratedKeySingleCreatorAdapter<K, T> implements GeneratedKeySingleCreator<K, T> {

    private Function<Collection<T>, Map<T, K>> delegate;

    public Function<Collection<T>, Map<T, K>> getDelegate() {
        return this.delegate;
    }

    public void setDelegate(final Function<Collection<T>, Map<T, K>> delegate) {
        this.delegate = delegate;
    }

    @Override
    public K createOne(final T data) {
        final Set<T> datas = Collections.singleton(data);
        final Map<T, K> retMap = this.delegate.apply(datas);
        return retMap.get(data);
    }

}
