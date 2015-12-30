package net.cultured.daoism.spring.jdbc;

import net.cultured.daoism.SingleUpdater;

public class SingleUpdaterImpl<T> extends SingleUpdateDAO<T> implements SingleUpdater<T> {

    @Override
    public void updateOne(final T data) {
        doUpdate(data);
    }

}
