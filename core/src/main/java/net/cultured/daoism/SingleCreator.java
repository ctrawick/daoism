package net.cultured.daoism;

import java.util.function.Consumer;

public interface SingleCreator<T> extends Consumer<T> {
    void createOne(T data);

    @Override
    default void accept(final T t) {
        createOne(t);
    }
}
