package com.yyq.backgroud.service;

import com.yyq.backgroud.bean.Meeting;
import com.yyq.backgroud.model.PageRequest;
import com.yyq.backgroud.model.PageResult;
import com.yyq.backgroud.model.RequestModel;

import java.util.List;

public interface MeetingService {
    /**
     * 添加会议
     */
    Object addMeeting(Meeting meeting) throws Exception ;

    /**
     * 修改会议
     * @param meeting
     * @return
     */

    Object updateMeeting(Meeting meeting);

    /**
     * 删除会议
     * @param id
     * @return
     */
    void deleteMeeting(Integer id);

    /**
     * 根据会议id查找 会议信息
     * @param id
     * @return
     */
    Object queryMeetingByMeetingId(int id);

    /**
     *  根据登录的用户，查询会议
     * @param
     * @return
     */
    List<Meeting> queryMeetingByUserName();

    /**
     *
     * @param meeting 模糊查询
     * @return
     */
    Object queryMeetingLikeMeeting(Meeting meeting);

    /**
     * 分页查询 会议信息
     */
    PageResult findAllMeeting(PageRequest pageRequest);

    /**
     *   模糊查询并支持分页
     * @param requestModel
     * @return
     */
    PageResult fuzzyQueryMeetingByPage(RequestModel requestModel);

    /**
     *  开启会议
     * @param id
     */
    void openMeeting(Integer id);

    /**
     * 关闭会议
     * @param id
     */
    void closeMeeting(Integer id);

    /**
     *
     */
    List<Meeting> queryMainMeetingByUser();
}
