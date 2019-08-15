package com.yyq.backgroud.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 *  会议议程实体类
 */
@Data
@Component
public class MeetingAgenda {
    private Integer id;
    private String type;                            // 议程类型
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String startTime;                         // 议程开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String endTime;                           // 议程结束时间
    private String isDelete;                        // 删除标记
    private String meetingId;                       // 会议id
    private Speaker speaker;                        // 演讲嘉宾

}
