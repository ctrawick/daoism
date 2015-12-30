package net.cultured.daoism.spring.jdbc;

import java.util.List;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class BatchOperation {
    private String sql;
    private List<SqlParameterSource> parameterBatch;

    public String getSql() {
        return this.sql;
    }

    public void setSql(final String sql) {
        this.sql = sql;
    }

    public List<SqlParameterSource> getParameterBatch() {
        return this.parameterBatch;
    }

    public void setParameterBatch(final List<SqlParameterSource> parameterBatch) {
        this.parameterBatch = parameterBatch;
    }
}
