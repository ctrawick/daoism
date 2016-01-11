package net.cultured.daoism.spring.jdbc;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class BatchOperation {
    private String sql;
    private SqlParameterSource[] parameterBatch;

    public String getSql() {
        return this.sql;
    }

    public void setSql(final String sql) {
        this.sql = sql;
    }

    public SqlParameterSource[] getParameterBatch() {
        return this.parameterBatch;
    }

    public void setParameterBatch(final SqlParameterSource[] parameterBatch) {
        this.parameterBatch = parameterBatch;
    }
}
