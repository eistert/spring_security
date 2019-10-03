package com.imooc.exception;

import java.io.Serializable;

public class UserNotExistException extends RuntimeException implements Serializable {


    private String id;


    public UserNotExistException(String id) {
        super("user not exist");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
