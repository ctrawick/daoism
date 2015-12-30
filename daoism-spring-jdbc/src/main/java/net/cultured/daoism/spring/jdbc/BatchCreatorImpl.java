package net.cultured.daoism.spring.jdbc;

import java.util.Collection;

import net.cultured.daoism.BatchCreator;

public class BatchCreatorImpl<T> extends BatchUpdateDAO<T> implements BatchCreator<T> {

    @Override
    public void createMany(final Collection<T> data) {
        doUpdate(data);
    }

}
