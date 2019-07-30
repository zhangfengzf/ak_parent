package com.yyq.backgroud.mapper;

import com.yyq.backgroud.bean.Meeting;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MeetingMapper {

    Integer insertMeeting(Meeting meeting);

    Object updateMeeting(Meeting meeting);

    Object deleteMeetingById(Integer id);
    Object queryMeetingByMeetingId(int id);
    List<Meeting> queryMeeting(@Param("username") String userName);
    List<Meeting> queryMeetingLikeMeeting(Meeting meeting);
    List<Meeting> selectMeetingPage(@Param("username") String userName);
    List<Meeting> findAllMainMeetingByCurrUser(@Param("username")String name);


}
