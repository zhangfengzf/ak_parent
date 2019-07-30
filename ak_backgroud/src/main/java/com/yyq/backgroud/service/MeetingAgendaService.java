package com.yyq.backgroud.service;

import com.yyq.backgroud.bean.Anchor;
import com.yyq.backgroud.bean.MeetingAgenda;

import java.util.List;

public interface MeetingAgendaService {
    /**
     *  添加主持人
     * @param anchor
     */
     void addAnchor(Anchor anchor);

    /**
     *   删除主持人
     * @param id
     */
     void deleteAnchor(Integer id);

    /**
     *  根据会议id，查询主持人
     * @param
     */
    List<Anchor> queryAnchors (Integer meetingid);

    /**
     * 添加议程
     * @param meetingAgenda
     */
    void addMeeingAgenda(MeetingAgenda meetingAgenda) throws Exception;

    /**
     *      根据会议id，查询当前会议id下的所有议程
     */
    List<MeetingAgenda> queryMeetingAgenda(Integer id);

    /**
     *  删除议程
     * @param id
     */
    void deleteMeetingAgeda(Integer id);
}
