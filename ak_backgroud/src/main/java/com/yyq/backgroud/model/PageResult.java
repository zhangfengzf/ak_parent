package com.yyq.backgroud.model;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class PageResult {

    // 每页展示条数
    private int pageSize;
     // 当前页数
    private int pageNum;
    //  总页数
    private int pages;
    // 记录总条数
    private long totalCount;

    private List<?> list;

}
