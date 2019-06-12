package com.initaitor.datamodel.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: ResponseEntity
 * @Description: TODO
 * @Author 张锋
 * @Date 2019/2/28 17:23
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntity implements Serializable {

    private Integer code;//状态码
    private String msg;//状态信息

}
