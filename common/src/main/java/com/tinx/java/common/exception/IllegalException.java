package com.tinx.java.common.exception;


import com.tinx.java.common.response.status.interfaces.Status;
import com.tinx.java.common.response.status.interfaces.StatusCode;

public class IllegalException extends RuntimeException {

    private static final long serialVersionUID = -3180156635518284727L;

    private Status status;


    public IllegalException(StatusCode statusCode) {
        super(statusCode.msg());
        this.status = statusCode;
    }

    public Status getStatus() {
        return status;
    }
}
