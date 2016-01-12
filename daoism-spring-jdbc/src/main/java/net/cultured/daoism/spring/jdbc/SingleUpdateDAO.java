package net.cultured.daoism.spring.jdbc;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class SingleUpdateDAO<T> extends NamedParameterJdbcDaoSupport {
    private Function<T, SingleOperation> operationDelegate;
    private BiFunction<Integer, T, Boolean> updateCountValidator = new DefaultSingleUpdateValidator<>();

    public Function<T, SingleOperation> getOperationDelegate() {
        return this.operationDelegate;
    }

    public void setOperationDelegate(final Function<T, SingleOperation> operationDelegate) {
        this.operationDelegate = operationDelegate;
    }

    public BiFunction<Integer, T, Boolean> getUpdateCountValidator() {
        return this.updateCountValidator;
    }

    public void setUpdateCountValidator(final BiFunction<Integer, T, Boolean> updateCountValidator) {
        this.updateCountValidator = updateCountValidator;
    }

    public void doUpdate(final T data) {
        // Get the update operation.
        final SingleOperation op = this.operationDelegate.apply(data);

        // Execute the update.
        final String sql = op.getSql();
        final SqlParameterSource paramSource = op.getParameters();
        final NamedParameterJdbcTemplate template = getNamedParameterJdbcTemplate();
        final int count = template.update(sql, paramSource);

        // Validate update counts using the configured validator.
        if (this.updateCountValidator != null && !this.updateCountValidator.apply(count, data)) {
            throw new InvalidUpdateCountException("Update count validation failed");
        }
    }
}
