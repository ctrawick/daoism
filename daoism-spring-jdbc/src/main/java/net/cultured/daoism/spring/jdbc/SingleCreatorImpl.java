package net.cultured.daoism.spring.jdbc;

import net.cultured.daoism.SingleCreator;

public class SingleCreatorImpl<T> extends SingleUpdateDAO<T> implements SingleCreator<T> {

    @Override
    public void createOne(final T data) {
        doUpdate(data);
    }

}
