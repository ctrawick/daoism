package net.cultured.daoism;

import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Exposes a {@link BatchCreator} as a {@link GeneratedKeyBatchCreator}. This
 * implementation uses a {@link SingleKeyGenerator} to generate keys and a
 * {@link KeySetter} to apply the generated keys to the data before using the
 * delegate to create the data records.
 *
 * @author chris
 *
 * @param <K>
 *            the key type
 * @param <T>
 *            the data type
 */
public class GeneratedKeyBatchCreatorAdapter<K, T> implements GeneratedKeyBatchCreator<K, T> {

    private Consumer<Collection<T>> delegate;
    private Supplier<K> generator;
    private BiConsumer<T, K> setter;

    /**
     * Get the batch creator delegate.
     *
     * @return a delegate
     * @see #setDelegate(Consumer)
     */
    public Consumer<Collection<T>> getDelegate() {
        return this.delegate;
    }

    /**
     * Set the batch creator delegate. This delegate will be used to create data
     * records one at a time.
     *
     * @param delegate
     *            a delegate
     */
    public void setDelegate(final Consumer<Collection<T>> delegate) {
        this.delegate = delegate;
    }

    /**
     * Get the key generator.
     *
     * @return a key generator
     * @see #setGenerator(Supplier)
     */
    public Supplier<K> getGenerator() {
        return this.generator;
    }

    /**
     * Set the key generator. The generator will be used to generate a key for
     * each of the input data records.
     *
     * @param generator
     *            a key generator
     */
    public void setGenerator(final Supplier<K> generator) {
        this.generator = generator;
    }

    /**
     * Get the key setter.
     *
     * @return a setter
     * @see #setSetter(BiConsumer)
     */
    public BiConsumer<T, K> getSetter() {
        return this.setter;
    }

    /**
     * Set the key setter. The setter will be used to apply the generated key to
     * each of the data elements.
     *
     * @param setter
     *            a setter
     */
    public void setSetter(final BiConsumer<T, K> setter) {
        this.setter = setter;
    }

    /**
     * Create multiple data records. This implementation will generate keys for
     * each record and apply them using a setter before calling the delegate.
     *
     * @return a map of the input elements to the generated keys
     * @throws NullPointerException
     *             if any of delegate, generator, or setter is null
     */
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
