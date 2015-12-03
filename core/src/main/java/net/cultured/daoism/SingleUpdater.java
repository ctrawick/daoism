package net.cultured.daoism;

import java.util.function.Consumer;

public interface SingleUpdater<T> extends Consumer<T> {
    void updateOne(T data);

    @Override
    default void accept(final T t) {
        updateOne(t);
    }
}
