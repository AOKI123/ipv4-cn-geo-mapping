package com.github.aoki123.ip.mapping.support;

/**
 * This class represents a non-specific error returned by MaxMind's
 * GeoIP2 web service. This occurs when the web service is up and
 * responding to requests, but the request sent was invalid in some way.
 */
public class InvalidRequestException extends GeoException {
    /**
     * @param message A message describing the reason why the exception was thrown.
     */
    public InvalidRequestException(String message) {
        super(message);
    }

    /**
     * @param message A message describing the reason why the exception was thrown.
     * @param cause
     */
    public InvalidRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
