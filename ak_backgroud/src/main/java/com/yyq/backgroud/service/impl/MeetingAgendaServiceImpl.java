package com.yyq.backgroud.service.impl;

import com.yyq.backgroud.bean.Anchor;
import com.yyq.backgroud.bean.MeetingAgenda;
import com.yyq.backgroud.bean.Speaker;
import com.yyq.backgroud.mapper.MeetingAgendaMapper;
import com.yyq.backgroud.service.MeetingAgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MeetingAgendaServiceImpl  implements MeetingAgendaService {
    @Autowired
    MeetingAgendaMapper meetingAgendaMapper;

    @Override
    public void addAnchor(Anchor anchor) {
        meetingAgendaMapper.insertAnchor(anchor);
    }

    @Override
    public void deleteAnchor(Integer id) {
        meetingAgendaMapper.deleteAnchor(id);
    }

    @Override
    public List<Anchor> queryAnchors(Integer Id) {
        return meetingAgendaMapper.queryAnchors(Id);
    }

    @Transactional
    @Override
    public void addMeetingAgenda(MeetingAgenda meetingAgenda) throws Exception {
        meetingAgendaMapper.insertMeetingAgenda(meetingAgenda);
        // 获取当前会议议程的id
        int agendaId  = meetingAgenda.getId();
        Speaker speaker = meetingAgenda.getSpeaker();
        if(speaker != null){
            speaker.setAgendaId(agendaId);
            meetingAgendaMapper.addSpeaker(speaker);
        }

    }

    @Override
    public List<MeetingAgenda> queryMeetingAgenda(Integer id) {
        return meetingAgendaMapper.queryAllAgendaByMeetingId(id);
    }

    @Override
    public void deleteMeetingAgenda(Integer id) {
        meetingAgendaMapper.deleteMeetingAgenda(id);
        meetingAgendaMapper.deleteSpeaker(id);
    }
}
