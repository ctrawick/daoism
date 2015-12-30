package net.cultured.daoism.spring.jdbc;

import net.cultured.daoism.SingleDeleter;

public class SingleDeleterImpl<K> extends SingleUpdateDAO<K> implements SingleDeleter<K> {

    @Override
    public void deleteOne(final K key) {
        doUpdate(key);
    }

}
