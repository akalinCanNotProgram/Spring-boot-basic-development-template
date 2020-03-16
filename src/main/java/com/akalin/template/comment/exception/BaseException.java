package com.akalin.template.comment.exception;

import lombok.Data;

/**
 * @author Exrickx
 */
@Data
public class BaseException extends RuntimeException {

    private String msg;

    public BaseException(String msg){
        super(msg);
        this.msg = msg;
    }
}
