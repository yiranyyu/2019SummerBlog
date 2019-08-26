package com.shimh.common.exception;


public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 3506744187536228284L;

    private String errCode;
    private String errMsg;

    public BaseException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
