package net.cultured.daoism;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;

public class BatchUpdaterAdapter<T> implements SingleUpdater<T> {

    private Consumer<Collection<T>> delegate;

    public Consumer<Collection<T>> getDelegate() {
        return this.delegate;
    }

    public void setDelegate(final Consumer<Collection<T>> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void updateOne(final T data) {
        this.delegate.accept(Collections.singleton(data));
    }

}
