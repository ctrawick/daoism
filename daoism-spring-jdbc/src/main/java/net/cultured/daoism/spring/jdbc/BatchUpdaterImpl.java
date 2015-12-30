package net.cultured.daoism.spring.jdbc;

import java.util.Collection;

import net.cultured.daoism.BatchUpdater;

public class BatchUpdaterImpl<T> extends BatchUpdateDAO<T> implements BatchUpdater<T> {

    @Override
    public void updateMany(final Collection<T> data) {
        doUpdate(data);
    }

}
