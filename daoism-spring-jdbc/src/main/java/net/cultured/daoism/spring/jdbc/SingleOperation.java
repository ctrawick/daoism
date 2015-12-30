package net.cultured.daoism.spring.jdbc;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class SingleOperation {
    private String sql;
    private SqlParameterSource parameters;

    public String getSql() {
        return this.sql;
    }

    public void setSql(final String sql) {
        this.sql = sql;
    }

    public SqlParameterSource getParameters() {
        return this.parameters;
    }

    public void setParameters(final SqlParameterSource parameters) {
        this.parameters = parameters;
    }
}
