package com.project.Exception;

public class AdministratorException extends Exception {
    public AdministratorException() {
        super();
    }

    public AdministratorException(String msg) {
        super(msg);
    }

    public AdministratorException(String msg, Throwable e) {
        super(msg, e);
    }
}
