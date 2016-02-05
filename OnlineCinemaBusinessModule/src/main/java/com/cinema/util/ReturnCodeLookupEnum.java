package com.cinema.util;

/**
 * Created by isaac on 2015/12/02.
 */


public enum ReturnCodeLookupEnum {


    SUCCESS(1, "Success"),
    USER_ALREADY_EXISTS(2,"");

    private ReturnCodeLookupEnum(Integer aReturnCode, String aReturnMessage) {

        this.returnCode = aReturnCode;
        this.returnMessage = aReturnMessage;
    }

    private final Integer returnCode;
    private final String returnMessage;

    public Integer getReturnCode() {
        return returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }


}
