package com.yyq.backgroud.model;

import com.yyq.backgroud.bean.Meeting;
import com.yyq.backgroud.bean.User;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class RequestModel {
    private User user;
    private PageRequest pageRequest;
    private Meeting meeting;
}
