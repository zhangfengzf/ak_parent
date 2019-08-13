package com.yyq.backgroud.controller;

import com.yyq.backgroud.bean.Meeting;
import com.yyq.backgroud.model.PageRequest;
import com.yyq.backgroud.model.PageResult;
import com.yyq.backgroud.model.RequestModel;
import com.yyq.backgroud.model.ResponseModel;
import com.yyq.backgroud.service.MeetingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 会议信息controller
 */
@RequestMapping("/meeting")
@RestController
@Api(value = "会议信息", description = "会议信息api")
public class MeetingController {
    @Autowired
    private MeetingService meetingService;


    @ApiOperation(value = "新建会议", notes = "添加会议信息")
    @RequestMapping(value = "/addMeeting", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addMeeting(@ApiParam(name = "meeting", value = "会议信息对象", required = true) @RequestBody Meeting meeting) {
        try{
            return ResponseEntity.ok(meetingService.addMeeting(meeting));
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseModel("", "error", "添加失败！", false));
        }
    }

    @PostMapping("/queryMainMeeting")
    @ApiOperation(value = "查询主会议", notes = "查询当前用户添加的会议类型为主会议，且状态为非已完成的会议列表")
    public ResponseEntity findMainMeetingByCurrentUser() {
        List<Meeting> meetings = meetingService.queryMainMeetingByUser();
        return ResponseEntity.ok(new ResponseModel(meetings, "", "未开启的主会议", true));
    }

    @ApiOperation(value = "编辑会议", notes = "修改会议信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateMeeting(@ApiParam(name = "meeting", value = "会议信息对象", required = true)
                                        @RequestBody Meeting meeting) {
        return ResponseEntity.ok(meetingService.updateMeeting(meeting));
    }

    @ApiOperation(value = "删除会议", notes = "删除会议信息")
    @GetMapping("/deleteMeeting")
    public ResponseEntity deleteMeeting(@ApiParam(name = "id", value = "会议id", required = true) @RequestParam  Integer id) {
        meetingService.deleteMeeting(id);
        return ResponseEntity.ok(new ResponseModel("","","删除成功！",true));

    }

    @ApiOperation(value = "查询会议", notes = "根据会议id，查询会议")
    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    public ResponseEntity queryMeetingById(@ApiParam(name = "id", value = "会议id", required = true) @RequestParam Integer id) {
        return ResponseEntity.ok(meetingService.queryMeetingByMeetingId(id));
    }

    @ApiOperation(value = "会议列表", notes = "并支持模糊查询" )
    @PostMapping(value = "/fuzzyQueryMeetingByPage", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity fuzzyQueryMeetingByPage(@ApiParam(name = "requestModel", value = "请求封装实体类", required = true) @RequestBody RequestModel requestModel) {
        PageResult pageResult = meetingService.fuzzyQueryMeetingByPage(requestModel);

        return ResponseEntity.ok(new ResponseModel(pageResult, "", "", true));
    }



    @ApiOperation(value = "开启会议", notes = "开启会议，修改会议状态")
    @GetMapping("/openMeeting")
    public void openMeeting(@ApiParam(name = "id", value = "会议id", required = true) @RequestParam("id") Integer id) {

        meetingService.openMeeting(id);
    }

    @ApiOperation(value = "停止会议", notes = "开启会议，修改会议状态")
    @GetMapping("/closeMeeting")
    public void closeMeeting(@ApiParam(name = "id", value = "会议id", required = true) @RequestParam("id") Integer id) {

        meetingService.closeMeeting(id);
    }

    @ApiOperation(value = "查询添加人", notes = "根据当前用户查询所有添加人")
    @PostMapping("/queryMeetingUserByCurrentUser")
    public ResponseEntity queryMeetingUserByCurrentUser(){
        List<Meeting> meetings = meetingService.queryMeetingByUserName();
        Set set = new HashSet();
        for(Meeting meeting:meetings){
            if(meeting.getUser()!=null) {
                set.add(meeting.getUser().getRealName());
            }
        }
        return ResponseEntity.ok(new ResponseModel(set,"","",true));
    }











    /**
     * @param meeting 模糊查询会议，不支持分页，目前不用
     * @return
     */

    @ApiOperation(value = "模糊查询", notes = "模糊查询会议")
    @RequestMapping(value = "/querylike", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity queryMeetingLike(@ApiParam(name = "meeting", value = "会议Meeting实体对象")
                                           @RequestBody Meeting meeting) {
        return ResponseEntity.ok(meetingService.queryMeetingLikeMeeting(meeting));
    }

    @ApiOperation(value = "会议列表,支持分页查询", notes = "根据当前登录用户查询当前用户所有会议")
    @PostMapping(value = "/queryMeetingByPage", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity selectMeetingPage(@RequestBody RequestModel requestModel) {

        PageRequest pageRequest = requestModel.getPageRequest();
        return ResponseEntity.ok(meetingService.findAllMeeting(pageRequest));
    }

    /**
     * 根据当前登录用户查询所有会议,不支持分页，目前不用
     *
     * @return
     */
    @ApiOperation(value = "会议列表", notes = "根据当前登录用户查询当前用户所有会议")
    @PostMapping(value = "/queryMeetingByUsername")
    public ResponseEntity queryMeeting() {
        return ResponseEntity.ok(meetingService.queryMeetingByUserName());
    }


}
