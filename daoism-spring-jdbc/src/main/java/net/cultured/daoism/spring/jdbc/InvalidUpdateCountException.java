package net.cultured.daoism.spring.jdbc;

public class InvalidUpdateCountException extends RuntimeException {

    private static final long serialVersionUID = 7009129814452450712L;

    public InvalidUpdateCountException() {
    }

    public InvalidUpdateCountException(final String message) {
        super(message);
    }

    public InvalidUpdateCountException(final Throwable cause) {
        super(cause);
    }

    public InvalidUpdateCountException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidUpdateCountException(final String message, final Throwable cause, final boolean enableSuppression,
            final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
