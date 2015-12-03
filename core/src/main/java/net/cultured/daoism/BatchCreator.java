package net.cultured.daoism;

import java.util.Collection;
import java.util.function.Consumer;

public interface BatchCreator<T> extends Consumer<Collection<T>> {
    void createMany(Collection<T> data);

    @Override
    default void accept(final Collection<T> t) {
        createMany(t);
    }
}
