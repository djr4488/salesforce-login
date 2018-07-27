package org.djr.salesforce.login;

public class SalesforceLoginException extends RuntimeException {
    public SalesforceLoginException() {
        super();
    }

    public SalesforceLoginException(String message) {
        super(message);
    }

    public SalesforceLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public SalesforceLoginException(Throwable cause) {
        super(cause);
    }

    protected SalesforceLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SalesforceLoginException(String message, String errorBody, int status) {
        super(message + " errorBody:" + errorBody + " status:" + status);
    }
}
