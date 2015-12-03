package net.cultured.daoism;

import java.util.Collection;
import java.util.function.Consumer;

public interface BatchUpdater<T> extends Consumer<Collection<T>> {
    void updateMany(Collection<T> data);

    @Override
    default void accept(final Collection<T> t) {
        updateMany(t);
    }
}
