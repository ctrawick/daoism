package net.cultured.daoism.spring.jdbc;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class SingleUpdateDAO<T> extends NamedParameterJdbcDaoSupport {
    private Logger log = LoggerFactory.getLogger(getClass());
    private Function<T, SingleOperationImpl> operationDelegate;

    public Logger getLog() {
        return this.log;
    }

    public void setLog(final Logger log) {
        this.log = log;
    }

    public Function<T, SingleOperationImpl> getOperationDelegate() {
        return this.operationDelegate;
    }

    public void setOperationDelegate(final Function<T, SingleOperationImpl> operationDelegate) {
        this.operationDelegate = operationDelegate;
    }

    public void doUpdate(final T data) {
        // Get the update operation.
        final SingleOperationImpl op = this.operationDelegate.apply(data);

        // Execute the update.
        final String sql = op.getSql();
        final SqlParameterSource paramSource = op.getParameters();
        final NamedParameterJdbcTemplate template = getNamedParameterJdbcTemplate();
        final int count = template.update(sql, paramSource);

        // Validate the update count is equal to 1 because the data object
        // should only affect one record. Otherwise, log a warning. All warnings
        // should be investigated as it is a potential application security
        // issue.
        if (count != 1) {
            final String fmt = "Invalid update count: expected 1, got %d (data=%s)";
            this.log.warn(fmt, count, data);
        }
    }
}
