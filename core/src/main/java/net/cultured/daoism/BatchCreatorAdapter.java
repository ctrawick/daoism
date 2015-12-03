package net.cultured.daoism;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Consumer;

public class BatchCreatorAdapter<T> implements SingleCreator<T> {

    private Consumer<Collection<T>> delegate;

    public Consumer<Collection<T>> getDelegate() {
        return this.delegate;
    }

    public void setDelegate(final Consumer<Collection<T>> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void createOne(final T data) {
        final Set<T> datas = Collections.singleton(data);
        this.delegate.accept(datas);
    }

}
