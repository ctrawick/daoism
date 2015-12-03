package net.cultured.daoism;

import java.util.function.Supplier;

public interface KeyGenerator<K> extends Supplier<K> {
    K generateKey();

    @Override
    default K get() {
        return generateKey();
    }
}
