package com.yyq.backgroud.controller;

import com.yyq.backgroud.bean.Anchor;
import com.yyq.backgroud.bean.MeetingAgenda;
import com.yyq.backgroud.model.ResponseModel;
import com.yyq.backgroud.service.MeetingAgendaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 会议议程controller
 */
@Api(value = "会议议程")
@RequestMapping("/agenda")
@RestController
public class MeetingAgendaController {

    @Autowired
    private MeetingAgendaService meetingAgendaService;
    @ApiOperation(value = "添加主持人")
    @PostMapping(value = "/addAnchor", consumes = MediaType.APPLICATION_JSON_VALUE )
    public void addAnchor(@RequestBody Anchor anchor){
        meetingAgendaService.addAnchor(anchor);
    }
    @ApiOperation(value = "删除主持人")
    @RequestMapping (value = "/deleteAnchor",method = RequestMethod.GET )
    public void deleteAnchor(@ApiParam(name = "id",value = "主持人id",required = true)
                                 @RequestParam Integer id){
        meetingAgendaService.deleteAnchor(id);
    }
    @ApiOperation(value = "查询主持人")
    @RequestMapping (value = "/queryAnchor",method = RequestMethod.GET )
    public ResponseEntity queryAnchor(@ApiParam(name = "id",value = "会议id",required = true)
                             @RequestParam Integer id){
        meetingAgendaService.queryAnchors(id);
        return ResponseEntity.ok(new ResponseModel(meetingAgendaService.queryAnchors(id),"","",true));
    }
    @ApiOperation(value = "添加会议议程")
    @RequestMapping(value = "/add",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addAgenda(@ApiParam(name = "meetingAgenda",value = "会议议程实体",required = true)
            @RequestBody MeetingAgenda meetingAgenda){
        try{
            meetingAgendaService.addMeeingAgenda(meetingAgenda);
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseModel("议程添加失败！","","",false));
        }

        return ResponseEntity.ok(new ResponseModel("议程添加成功！","","",true));
    }
    @ApiOperation(value = "查询会议议程")
    @RequestMapping(value = "/queryAgenda",method = RequestMethod.GET)
    public ResponseEntity queryAgenda(@ApiParam(name = "id",value = "会议id",required = true)
                                          @RequestParam  Integer id){
        return ResponseEntity.ok(new ResponseModel(meetingAgendaService.queryMeetingAgenda(id),"","",true));
    }

    @ApiOperation(value = "删除会议议程")
    @RequestMapping(value = "/deleteAgenda",method = RequestMethod.GET)
    public void deleteAgenda(@ApiParam(name = "id",value = "会议议程id",required = true)
                                 @RequestParam  Integer id){
        meetingAgendaService.deleteMeetingAgeda(id);

    }

}
