package com.initaitor.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: ResponseEntity
 * @Description: TODO
 * @Author 张锋
 * @Date 2019/2/28 17:23
 * @Version 1.0
 */
@Data
public class ResponseEntity implements Serializable {

    /*状态码*/
    private Integer code;
    /*状态信息*/
    private String msg;

    public ResponseEntity() {}

    public ResponseEntity(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
