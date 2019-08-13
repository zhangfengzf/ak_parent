package com.yyq.backgroud.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 会议实体类
 */
@Data
@Component
public class Meeting implements Serializable {
    private Integer id;
    private String  meetingType;                                    // 会议类型
    private String  meetingName;                                    // 会议名称
    private String  address;                                        // 地址
    private String  meetingScale;                                   // 会议规模
    private String  meetingBill;                                    // 会议海报
    private String  meetingContext;                                 // 会议简介
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;                                         // 开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;                                           // 结束时间
    private String isDelete;                                        // 删除标记
    private String state;                                           // 会议状态
    private int  mainId;                                            // 主会议id
    private User user;                                              // 录入用户
    private List<MeetingAgenda> meetingAgenda;                      // 会议议程
    private List<Anchor> anchors;                                   // 主持人
    private List<Meeting> children;                               // 分会议
}
