package com.example.initiator.pojo;

import java.io.Serializable;

/**
 * @ClassName: ResponseEntity
 * @Description: TODO
 * @Author 张锋
 * @Date 2019/2/28 17:23
 * @Version 1.0
 */
public class ResponseEntity implements Serializable {

    private Integer code;//状态码
    private String msg;//状态信息

    public ResponseEntity(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
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
}
