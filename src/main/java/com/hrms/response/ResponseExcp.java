package com.hrms.response;

import com.hrms.enumUtil.ResponseCodeEnum;
import java.io.Serializable;

/**
 * 异常通用响应体
 * Created by QSJ on 2018/11/30.
 */
public class ResponseExcp implements Serializable{

    private String message;

    private String code;

    private Object data;

    private String url;

    public ResponseExcp(String message,String code,Object data,String url){
        this.message = message;
        this.code = code;
        this.data = data;
        this.url = url;
    }

    public static ResponseExcp success(String msg,Object data,String url){
        return new ResponseExcp(msg, ResponseCodeEnum.SUCCESS.getCode(),data,url);
    }

    public static ResponseExcp fail(String msg,Object data,String url){
        return new ResponseExcp(msg, ResponseCodeEnum.TOKEN_ERROR.getCode(),data,url);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
