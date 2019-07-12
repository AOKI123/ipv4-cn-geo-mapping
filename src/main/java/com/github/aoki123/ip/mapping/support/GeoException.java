package com.github.aoki123.ip.mapping.support;

/**
 * This class represents a generic GeoIP2 error. All other exceptions thrown
 * by the GeoIP2 API subclass this exception
 */
public class GeoException extends RuntimeException {

    private static final long serialVersionUID = -1923104535309628719L;

    /**
     * @param message A message describing the reason why the exception was thrown.
     */
    public GeoException(String message) {
        super(message);
    }

    /**
     * @param message A message describing the reason why the exception was thrown.
     * @param cause   The cause of the exception.
     */
    public GeoException(String message, Throwable cause) {
        super(message, cause);
    }
}
