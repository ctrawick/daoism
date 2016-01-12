package net.cultured.daoism.spring.jdbc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class BatchUpdateDAO<T> extends NamedParameterJdbcDaoSupport {

    private Function<Collection<T>, BatchOperation> operationDelegate;
    private BiFunction<List<Integer>, List<T>, Boolean> updateCountValidator = new DefaultBatchUpdateValidator<>();
    private Class<T> dataClass;

    public Function<Collection<T>, BatchOperation> getOperationDelegate() {
        return this.operationDelegate;
    }

    public void setOperationDelegate(final Function<Collection<T>, BatchOperation> operationDelegate) {
        this.operationDelegate = operationDelegate;
    }

    public BiFunction<List<Integer>, List<T>, Boolean> getUpdateCountValidator() {
        return this.updateCountValidator;
    }

    public void setUpdateCountValidator(final BiFunction<List<Integer>, List<T>, Boolean> updateCountValidator) {
        this.updateCountValidator = updateCountValidator;
    }

    public Class<T> getDataClass() {
        return this.dataClass;
    }

    public void setDataClass(final Class<T> dataClass) {
        this.dataClass = dataClass;
    }

    /**
     * Perform a batch update. This implementation obtains SQL and batch
     * parameters from the operation delegate, calls the Spring JDBC template,
     * and validates each update count.
     *
     * @param batch
     *            the data to update
     * @see NamedParameterJdbcTemplate#batchUpdate(String, SqlParameterSource[])
     * @throws NullPointerException
     *             if {@link #setOperationDelegate(Function)} hasn't been called
     *             with a non-null reference
     * @throws NullPointerException
     *             if {@link #setDataClass(Class)} hasn't been called with a
     *             non-null reference
     * @throws InvalidUpdateCountException
     *             if the update count validator returns false
     */
    public void doUpdate(final Collection<T> batch) {
        // Collections don't lock in a data order, so convert the batch to a
        // list.
        final List<T> data = new ArrayList<>(batch);

        // Get the batch operation.
        final BatchOperation op = this.operationDelegate.apply(data);

        // Execute the batch.
        final String sql = op.getSql();
        final SqlParameterSource[] parameterBatch = op.getParameterBatch();
        final NamedParameterJdbcTemplate template = getNamedParameterJdbcTemplate();
        final int[] updateCounts = template.batchUpdate(sql, parameterBatch);

        if (this.updateCountValidator != null) {
            // Validate update counts using the configured validator.
            final List<Integer> countList = Arrays.stream(updateCounts).boxed().collect(Collectors.toList());
            if (!this.updateCountValidator.apply(countList, data)) {
                throw new InvalidUpdateCountException("Update count validation failed");
            }
        }
    }
}
