package com.yyq.backgroud.bean;

import lombok.Data;
import org.springframework.stereotype.Component;


/**
 *演讲嘉宾实体类
 */
@Data
@Component
public class Speaker {
    private Integer id;
    private String speakerName;                                  // 演讲嘉宾姓名
    private String speakerIntroduce;                             // 嘉宾介绍
    private String photo;                                        // 照片
    private String title;                                        // 主题
    private String isDelete;                                     // 删除标记
    private Integer agendaId;                                    // 议程id
    private Integer meetingId;                                   // 会议id


}
