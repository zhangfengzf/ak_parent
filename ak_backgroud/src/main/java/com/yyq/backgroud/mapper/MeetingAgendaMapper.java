package com.yyq.backgroud.mapper;

import com.yyq.backgroud.bean.Anchor;
import com.yyq.backgroud.bean.MeetingAgenda;
import com.yyq.backgroud.bean.Speaker;

import java.util.List;

public interface MeetingAgendaMapper {
    int insertAnchor(Anchor anchor);
    void deleteAnchor(Integer id);
    void deleteSpeaker(Integer id);
    List<Anchor> queryAnchors (Integer meetingId);
    void addSpeaker(Speaker speaker);
    void insertMeetingAgenda(MeetingAgenda meetingAgenda);
    List<MeetingAgenda> queryAllAgendaByMeetingId(Integer id);
    void deleteMeetingAgenda(Integer id);
}
