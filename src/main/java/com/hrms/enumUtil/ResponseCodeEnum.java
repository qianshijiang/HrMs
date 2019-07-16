package com.hrms.enumUtil;

/**
 * 作为响应请求的枚举
 * Created by QSJ on 2018/11/29.
 */
public enum ResponseCodeEnum {

    SUCCESS("00000", "SUCCESS"),
    API_KEY_NOT_AUTH("101", "API_KEY_NOT_AUTH"),
    CALLTIME_EXPIRED("102", "CALLTIME_EXPIRED"),
    KEY_NOT_NULL("103", "KEY_NOT_NULL"),
    VALUE_NOT_NULL("104", "VALUE_NOT_NULL"),
    WARN("10000", "WARN"),
    WARN_OPERATOR("10001", "WARN"),
    BIZ_ERROR("00009", "BIZ_ERROR"),
    IS_EXIST("301", "IS_EXIST"),
    NAME_IS_EXIST("302", "NAME_IS_EXIST"),
    THIS_CARD_NO_REGISTER("30001", "THIS_CARD_NO_REGISTER"),
    THIS_CARD_ALREADY_LOSS("30002", "THIS_CARD_ALREADY_LOSS"),
    THIS_CARD_ALREADY_RETURN("30003", "THIS_CARD_ALREADY_RETURN"),
    THIS_CARD_ALREADY_CANCEL("30004", "THIS_CARD_ALREADY_CANCEL"),
    API_ERROR("00001", "API_ERROR"),
    SQL_ERROR("00002", "SQL_ERROR"),
    HTTP_ERROR("00003", "HTTP_ERROR"),
    WS_ENTER_ERROR("00004", "WS_ENTER_ERROR"),
    WS_OUTER_ERROR("00005", "WS_OUTER_ERROR"),
    PARAMETER_EXCEPTION("00006", "PARAMETER_EXCEPTION"),
    I18N_ERROR("00007", "I18N_ERROR"),
    TOKEN_ERROR("400", "TOKEN_ERROR"),
    ERROR("300", "ERROR");

    private String code;
    private String messageKey;

    private ResponseCodeEnum(String code, String messageKey) {
        this.code = code;
        this.messageKey = messageKey;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessageKey() {
        return this.messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

}
