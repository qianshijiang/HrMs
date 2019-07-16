package com.hrms.response;

import com.hrms.enumUtil.ResponseCodeEnum;
import java.io.Serializable;

/**
 * @author QSJ
 * @describe 作为统一的响应对象
 * @date 2018-04-10
 */
public class ResultModel extends PageBean implements Serializable {

	public static final String SUCCESS_CODE;
    public static final String ERROR_CODE;
    public static final String WARN_CODE;
    public static final String WARN_OPERATOR_CODE;
    private String code;
    private Object data;
    private String message;
    private String error;

    public ResultModel() {
        this.code = SUCCESS_CODE;
    }

    public ResultModel(String code, Object data, String message, String error) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.error = error;
    }

    public ResultModel(Integer currentPage, Integer pageSize, Integer totalNum) {
        super(currentPage,pageSize,totalNum);
    }

    public static ResultModel success(String message, Object data) {
        return new ResultModel(SUCCESS_CODE, data, message, "");
    }

    public static ResultModel successfull(String message,ResultModel resultModel){
        resultModel.setMessage(message);
        resultModel.setCode(SUCCESS_CODE);
        resultModel.setError("");
        return resultModel;
    }

    public static ResultModel success(Object data) {
        return new ResultModel(SUCCESS_CODE, data, "", "");
    }

    public static ResultModel success() {
        return new ResultModel(SUCCESS_CODE, (Object)null, "", "");
    }

    /*public static ResultModel successI18n(String messageKey, Object data, Object... params) {
        String message = LocaleResolverUtil.getMessage(messageKey, params);
        return new ResultModel(SUCCESS_CODE, data, message, "");
    }

    public static ResultModel warnI18n(String messageKey, Object data, Object... params) {
        String message = LocaleResolverUtil.getMessage(messageKey, params);
        return new ResultModel(WARN_CODE, data, message, "");
    }

    public static ResultModel warnAndOperatorI18n(String messageKey, Object data, Object... params) {
        String message = LocaleResolverUtil.getMessage(messageKey, params);
        return new ResultModel(WARN_OPERATOR_CODE, data, message, "");
    }*/

    public static ResultModel fail(String message, String error) {
        return new ResultModel(ERROR_CODE, (Object)null, message, error);
    }

    public static ResultModel failed(String message, Object object) {
        return new ResultModel(ERROR_CODE, object, message, "");
    }

    public static ResultModel failfull(String message, String error,ResultModel resultModel) {
        resultModel.setMessage(message);
        resultModel.setCode(ERROR_CODE);
        resultModel.setError("");
        return resultModel;
    }

    /*public static ResultModel failI18n(ResponseCodeEnum responseCodeEnum, String error, Object... params) {
        String message = LocaleResolverUtil.getMessage(responseCodeEnum.getMessageKey(), params);
        return new ResultModel(responseCodeEnum.getCode(), (Object)null, message, error);
    }*/

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return null != this.data && !"null".equals(this.data)?this.data:"";
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /*public void setMessageI18n(String messageKey, Object... params) {
        String message = LocaleResolverUtil.getMessage(messageKey, params);
        this.message = message;
    }*/

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    static {
        SUCCESS_CODE = ResponseCodeEnum.SUCCESS.getCode();
        ERROR_CODE = ResponseCodeEnum.ERROR.getCode();
        WARN_CODE = ResponseCodeEnum.WARN.getCode();
        WARN_OPERATOR_CODE = ResponseCodeEnum.WARN_OPERATOR.getCode();
    }
}