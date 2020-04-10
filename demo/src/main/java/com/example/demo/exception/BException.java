package com.example.demo.exception;

/**
 * 异常类
 */
public class BException extends RuntimeException {
	
    private Integer code = -1;
    private static final long serialVersionUID = -2623309261327598087L;
    private String msg = "失败";

    public BException(String msg) {
        super(msg);
        this.msg=msg;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BException(Exception cause) {
        super(cause);
    }
    
    public BException(Integer code, String msg) {
    	super(msg);
    	this.code = code;
    	this.msg = msg;
    }

    public BException(String msg, Exception cause) {
        super(msg, cause);
    }

    public BException(Integer code, String msg, Exception cause) {
        super(msg, cause);
        this.code = code;

    }

    public Integer getCode() {
        return this.code;
    }
}