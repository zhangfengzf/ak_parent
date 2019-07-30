package com.yyq.backgroud.model;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 *  分页查询 请求封装类
 */
@Data
@Component
public class PageRequest {
    //  每页展示条数
    private int pageSize;
    //  当前页码
    private int pageNum;
}
