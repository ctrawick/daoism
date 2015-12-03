package net.cultured.daoism;

import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KeyedBatchUpdaterAdapter<K, T> implements BatchUpdater<T> {

    private Consumer<Map<K, T>> delegate;
    private Function<T, K> getter;

    public Consumer<Map<K, T>> getDelegate() {
        return this.delegate;
    }

    public void setDelegate(final Consumer<Map<K, T>> delegate) {
        this.delegate = delegate;
    }

    public Function<T, K> getGetter() {
        return this.getter;
    }

    public void setGetter(final Function<T, K> getter) {
        this.getter = getter;
    }

    @Override
    public void updateMany(final Collection<T> data) {
        final Stream<T> dataStream = data.stream();
        final Function<? super T, ? extends K> keyMapper = d -> this.getter.apply(d);
        final Collector<T, ?, Map<K, T>> mapCollector = Collectors.toMap(keyMapper, Function.identity());
        final Map<K, T> dataMap = dataStream.collect(mapCollector);
        this.delegate.accept(dataMap);
    }

}
