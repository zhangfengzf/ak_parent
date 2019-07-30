package com.yyq.backgroud.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *  会议议程实体类
 */
@Data
@Component
public class MeetingAgenda {
    private Integer id;
    private String type;                            // 议程类型
    private Date startTime;                         // 议程开始时间
    private Date endTime;                           // 议程结束时间
    private String isDelete;                        // 删除标记
    private String meetingId;                       // 会议id
    private Speaker speaker;                        // 演讲嘉宾

}
