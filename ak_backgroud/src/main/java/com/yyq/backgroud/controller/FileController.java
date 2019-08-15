package com.yyq.backgroud.controller;

import com.yyq.backgroud.model.ResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Api(value = "文件上传下载")
@RequestMapping("/file")
@RestController
public class FileController {
    @Value("${auto.path}")
    private String path;
    @ApiOperation(value = "上传海报")
    @CrossOrigin
    @RequestMapping(value = "/uploadBill",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity upload( MultipartFile file) {
        String filepath =" ";
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = simpleDateFormat.format(date);
        try{
            String filename = file.getOriginalFilename();
            filepath = path + format + filename;
            File imagefile = new File(filepath);
            file.transferTo(imagefile);

        }catch (IOException e){
            e.printStackTrace();
            return ResponseEntity.ok(new ResponseModel("","","文件上传失败！",false));
        }
        return ResponseEntity.ok(new ResponseModel(filepath,"","文件上传成功！",true));
    }

}
