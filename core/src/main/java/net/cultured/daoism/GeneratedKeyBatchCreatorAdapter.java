package net.cultured.daoism;

import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GeneratedKeyBatchCreatorAdapter<K, T> implements GeneratedKeyBatchCreator<K, T> {

    private Consumer<Collection<T>> delegate;
    private Supplier<K> generator;
    private BiConsumer<T, K> setter;

    public Consumer<Collection<T>> getDelegate() {
        return this.delegate;
    }

    public void setDelegate(final Consumer<Collection<T>> delegate) {
        this.delegate = delegate;
    }

    public Supplier<K> getGenerator() {
        return this.generator;
    }

    public void setGenerator(final Supplier<K> generator) {
        this.generator = generator;
    }

    public BiConsumer<T, K> getSetter() {
        return this.setter;
    }

    public void setSetter(final BiConsumer<T, K> setter) {
        this.setter = setter;
    }

    @Override
    public Map<T, K> createMany(final Collection<T> data) {
        final Map<T, K> ret = new IdentityHashMap<>();
        data.forEach(d -> {
            final K key = this.generator.get();
            this.setter.accept(d, key);
            ret.put(d, key);
        });
        this.delegate.accept(data);
        return ret;
    }

}
