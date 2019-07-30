package com.yyq.backgroud.model;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

@Component
public class PageUtil {
    public static PageResult getPageResult(PageRequest pageRequest, PageInfo page) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageRequest.getPageNum());
        pageResult.setPageSize(pageRequest.getPageSize());
        pageResult.setPages(page.getPages());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setList(page.getList());
        return pageResult;
    }
}
