package net.cultured.daoism.spring.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSingleUpdateValidator<T> implements SingleUpdateValidator<T> {
    private Logger log = LoggerFactory.getLogger(getClass());

    public DefaultSingleUpdateValidator() {
        // TODO Auto-generated constructor stub
    }

    public Logger getLog() {
        return this.log;
    }

    public void setLog(final Logger log) {
        this.log = log;
    }

    /**
     * Validate that the update count is exactly one. Normally, all counts
     * should equal 1 because each batch entry should only affect one record.
     * Otherwise, log a warning. All such warnings should be investigated as it
     * is a potential application security issue.
     */
    @Override
    public Boolean validateSingleUpdate(final int updateCount, final T data) {
        if (updateCount == 1) {
            return true;
        }
        final String fmt = "Invalid update count: expected 1, got %d (data=%s)";
        this.log.warn(fmt, updateCount, data);
        return false;
    }

}
