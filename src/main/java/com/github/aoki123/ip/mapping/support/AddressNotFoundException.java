package com.github.aoki123.ip.mapping.support;

/**
 * This exception is thrown when the IP address is not found in the
 * database. This generally means that the address was a private or
 * reserved address.
 */
public class AddressNotFoundException extends GeoException {

    /**
     * @param message A message describing the reason why the exception was thrown.
     */
    public AddressNotFoundException(String message) {
        super(message);
    }

    /**
     * @param message A message describing the reason why the exception was thrown.
     * @param cause
     */
    public AddressNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
