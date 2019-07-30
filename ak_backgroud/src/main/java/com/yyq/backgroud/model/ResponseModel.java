package com.yyq.backgroud.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseModel {

    public ResponseModel(Object data, String error, String msg, boolean success) {
        this.data = data;
        this.error = error;
        this.msg = msg;
        this.success = success;
    }

    private Object data;

    private String error;

    private String msg;

    private boolean success;

    @Override
    public String toString() {
        return "ResponseModel{" +
                "data=" + data +
                ", error='" + error + '\'' +
                ", msg='" + msg + '\'' +
                ", success=" + success +
                '}';
    }
}
