package com.yyq.backgroud.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyq.backgroud.bean.Meeting;
import com.yyq.backgroud.bean.User;
import com.yyq.backgroud.mapper.MeetingMapper;
import com.yyq.backgroud.model.*;
import com.yyq.backgroud.service.MeetingService;
import com.yyq.backgroud.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    MeetingMapper meetingMapper;

    @Override
    public Object addMeeting(Meeting meeting) throws Exception {
        User user = new User();
        String userName = UserUtil.getCurrentUser();
        user.setUsername(userName);
        meeting.setUser(user);
        meetingMapper.insertMeeting(meeting);
        return new ResponseModel(meeting.getId(), "", "成功添加会议！", true);
    }

    @Override
    public Object updateMeeting(Meeting meeting) {
        return meetingMapper.updateMeeting(meeting);
    }

    @Override
    public void deleteMeeting(Integer id) {
         meetingMapper.deleteMeetingById(id);
    }


    @Override
    public  List<Meeting> queryMeetingByUserName() {
        List<Meeting> meetings = meetingMapper.queryMeeting(getCurrentUser());
        return meetings;
    }

    @Override
    public Object queryMeetingByMeetingId(int id) {
        Meeting meeting = (Meeting) meetingMapper.queryMeetingByMeetingId(id);
        return new ResponseModel(meeting, "", "", true);
    }

    @Override
    public Object queryMeetingLikeMeeting(Meeting meeting) {
        User userlike = meeting.getUser();
        if (userlike == null) {
            User user = new User();
            String username = getCurrentUser();
            user.setUsername(username);
            meeting.setUser(user);
        }
        List<Meeting> meetings = meetingMapper.queryMeetingLikeMeeting(meeting);
        return new ResponseModel(meetings, "", "", true);
    }

    /**
     * 判断当前登录用户是  普通用户还是管理员，管理员  username == null
     *
     * @return
     */
    public String getCurrentUser() {
        String username = UserUtil.getCurrentUser();
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        String role = "";
        for (GrantedAuthority grantedAuthority : authorities) {
            role = grantedAuthority.getAuthority();
        }
        if ("role_admin".equals(role)) {
            username = null;
        }
        return username;
    }

    @Override
    public PageResult findAllMeeting(PageRequest pageRequest) {
        return PageUtil.getPageResult(pageRequest, getPageInfo(pageRequest));
    }

    public PageInfo getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Meeting> meetings = meetingMapper.selectMeetingPage(getCurrentUser());
        return new PageInfo(meetings);

    }

    @Override
    public PageResult fuzzyQueryMeetingByPage(RequestModel requestModel) {
        PageRequest pageRequest = requestModel.getPageRequest();
        Meeting meeting = requestModel.getMeeting();
        if (meeting != null) {
            User user = meeting.getUser();
            if (user == null) {
                String currentUserName = UserUtil.getCurrentUser();
                User userNew = new User();
                userNew.setUsername(currentUserName);
                meeting.setUser(userNew);
            }
        } else {
            meeting = new Meeting();
        }
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        // 查询所有的主会议
        List<Meeting> meetingList = meetingMapper.queryMeetingLikeMeeting(meeting);
        // 查询所有的分会议
        List<Meeting> subMeeting= meetingMapper.querySubMeeting();
        for(Meeting m : meetingList){
            List list = new ArrayList();
            for(Meeting sub : subMeeting){
                if( m.getId() == sub.getMainId()){
                    list.add(sub);
                }
            }
            m.setChildren(list);
        }
        System.out.println(meetingList);
        PageInfo<Meeting> pageInfo = new PageInfo(meetingList);
        return PageUtil.getPageResult(pageRequest, pageInfo);

    }

    @Override
    public void openMeeting(Integer id) {

        Meeting meeting = (Meeting) meetingMapper.queryMeetingByMeetingId(id);
        // meeting.state   1：未开始，  2 ：已启动，   3 ：已结束
        meeting.setState("2");
        meetingMapper.updateMeeting(meeting);
    }

    @Override
    public void closeMeeting(Integer id) {
        Meeting meeting = (Meeting) meetingMapper.queryMeetingByMeetingId(id);
        meeting.setState("3");
        meetingMapper.updateMeeting(meeting);
    }

    @Override
    public List<Meeting> queryMainMeetingByUser() {

        return meetingMapper.findAllMainMeetingByCurrUser(getCurrentUser());
    }
}
