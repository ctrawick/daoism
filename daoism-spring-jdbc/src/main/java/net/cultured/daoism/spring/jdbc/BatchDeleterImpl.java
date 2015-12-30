package net.cultured.daoism.spring.jdbc;

import java.util.Collection;

import net.cultured.daoism.BatchCreator;
import net.cultured.daoism.BatchDeleter;

public class BatchDeleterImpl<K> extends BatchUpdateDAO<K> implements BatchDeleter<K> {

    @Override
    public void deleteMany(final Collection<K> keys) {
        doUpdate(keys);
    }

}
