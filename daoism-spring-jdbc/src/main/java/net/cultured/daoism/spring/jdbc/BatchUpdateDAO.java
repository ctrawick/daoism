package net.cultured.daoism.spring.jdbc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class BatchUpdateDAO<T> extends NamedParameterJdbcDaoSupport {
    private Logger log = LoggerFactory.getLogger(getClass());
    private Function<Collection<T>, BatchOperationImpl> operationDelegate;

    public Logger getLog() {
        return this.log;
    }

    public void setLog(final Logger log) {
        this.log = log;
    }

    public Function<Collection<T>, BatchOperationImpl> getOperationDelegate() {
        return this.operationDelegate;
    }

    public void setOperationDelegate(final Function<Collection<T>, BatchOperationImpl> operationDelegate) {
        this.operationDelegate = operationDelegate;
    }

    public void doUpdate(final Collection<T> batch) {
        // Get the batch operation.
        final BatchOperationImpl op = this.operationDelegate.apply(batch);

        // Execute the batch.
        final String sql = op.getSql();
        final List<SqlParameterSource> parameterBatch = op.getParameterBatch();
        final NamedParameterJdbcTemplate template = getNamedParameterJdbcTemplate();
        final int batchSize = parameterBatch.size();
        final SqlParameterSource[] batchValues = parameterBatch.toArray(new SqlParameterSource[batchSize]);
        final int[] counts = template.batchUpdate(sql, batchValues);

        // Validate update counts. All counts should equal 1 because each batch
        // entry should only affect one record. Otherwise, log a warning. All
        // warnings should be investigated as it is a potential application
        // security issue.
        for (int i = 0; i < counts.length; i++) {
            final int count = counts[i];
            if (count != 1) {
                final String fmt = "Invalid update count: expected 1, got %d (data=%s)";
                final T data = new ArrayList<>(batch).get(i);
                this.log.warn(fmt, count, data);
            }
        }
    }
}
