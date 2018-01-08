package com.project.Exception;

public class ClientException extends Exception{
    public ClientException() {
        super();
    }

    public ClientException(String msg) {
        super(msg);
    }

    public ClientException(String msg, Throwable e) {
        super(msg,e);
    }

}
