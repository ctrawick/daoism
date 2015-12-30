package net.cultured.daoism.spring.jdbc;

import java.util.function.Function;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import net.cultured.daoism.SingleReader;

public class SingleReaderImpl<K, T> extends NamedParameterJdbcDaoSupport implements SingleReader<K, T> {
    private Function<K, SingleOperation> operationDelegate;
    private RowMapper<T> rowMapper;

    public Function<K, SingleOperation> getOperationDelegate() {
        return this.operationDelegate;
    }

    public void setOperationDelegate(final Function<K, SingleOperation> operationDelegate) {
        this.operationDelegate = operationDelegate;
    }

    public RowMapper<T> getRowMapper() {
        return this.rowMapper;
    }

    public void setRowMapper(final RowMapper<T> rowMapper) {
        this.rowMapper = rowMapper;
    }

    @Override
    public T readOne(final K key) {
        final SingleOperation op = this.operationDelegate.apply(key);
        final String sql = op.getSql();
        final SqlParameterSource parameters = op.getParameters();
        final NamedParameterJdbcTemplate template = getNamedParameterJdbcTemplate();
        return template.queryForObject(sql, parameters, this.rowMapper);
    }

}
