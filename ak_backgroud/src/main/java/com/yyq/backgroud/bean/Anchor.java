package com.yyq.backgroud.bean;

import lombok.Data;
import org.springframework.stereotype.Component;


/**
 *主持人实体类
 */
@Data
@Component
public class Anchor {
    private Integer id;
    private String anchorName;                       //   主持人姓名
    private String anchorIntroduce;                  //   主持人介绍
    private Integer isDelete;                        //   删除标记
    private Integer meetingId;                       //   会议id
}
