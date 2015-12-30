package net.cultured.daoism.spring.jdbc;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import net.cultured.daoism.BatchReader;

public class BatchReaderImpl<K, T> extends NamedParameterJdbcDaoSupport implements BatchReader<K, T> {
    private Logger log = LoggerFactory.getLogger(getClass());
    private Function<Collection<K>, SingleOperationImpl> operationDelegate;
    private RowMapper<T> rowMapper;
    private Supplier<Map<K, T>> mapSupplier = () -> new LinkedHashMap<>();
    private Function<T, K> keyGetter;

    public Logger getLog() {
        return this.log;
    }

    public void setLog(final Logger log) {
        this.log = log;
    }

    public Function<Collection<K>, SingleOperationImpl> getOperationDelegate() {
        return this.operationDelegate;
    }

    public void setOperationDelegate(final Function<Collection<K>, SingleOperationImpl> operationDelegate) {
        this.operationDelegate = operationDelegate;
    }

    public RowMapper<T> getRowMapper() {
        return this.rowMapper;
    }

    public void setRowMapper(final RowMapper<T> rowMapper) {
        this.rowMapper = rowMapper;
    }

    public Supplier<Map<K, T>> getMapSupplier() {
        return this.mapSupplier;
    }

    public void setMapSupplier(final Supplier<Map<K, T>> mapSupplier) {
        this.mapSupplier = mapSupplier;
    }

    public Function<T, K> getKeyGetter() {
        return this.keyGetter;
    }

    public void setKeyGetter(final Function<T, K> keyGetter) {
        this.keyGetter = keyGetter;
    }

    @Override
    public Map<K, T> readMany(final Collection<K> keys) {
        // Get the batch operation.
        final SingleOperationImpl op = this.operationDelegate.apply(keys);

        // Execute the query.
        final String sql = op.getSql();
        final SqlParameterSource parameters = op.getParameters();
        final NamedParameterJdbcTemplate template = getNamedParameterJdbcTemplate();
        final List<T> results = template.query(sql, parameters, this.rowMapper);

        // Populate the return map.
        final Map<K, T> ret = this.mapSupplier.get();
        for (final T result : results) {
            final K key = this.keyGetter.apply(result);
            final T old = ret.put(key, result);
            if (old != null) {
                final String fmt = "Multiple results found for key '%s', discarding result: %s";
                this.log.warn(fmt, key, old);
            }
        }
        return ret;
    }

}
