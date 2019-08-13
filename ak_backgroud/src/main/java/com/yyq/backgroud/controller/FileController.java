package com.yyq.backgroud.controller;

import com.yyq.backgroud.model.ResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Api(value = "文件上传下载")
@RequestMapping("/file")
@RestController
public class FileController {
    @Value("${auto.path}")
    private String path;
    @ApiOperation(value = "上传海报")
    @RequestMapping(value = "uploadBill",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity upload(@RequestParam("file") MultipartFile file) {
        String filepath ="";
        try{
            String filename = file.getOriginalFilename();
            filepath = path + filename;
            File imagefile = new File(filepath);
            file.transferTo(imagefile);

        }catch (IOException e){
            e.printStackTrace();
            return ResponseEntity.ok(new ResponseModel("","","文件上传失败！",false));
        }
        return ResponseEntity.ok(new ResponseModel(filepath,"","文件上传成功！",true));

    }
    /*@ApiOperation(value = "海报下载")
    @RequestMapping(value = "/down",method = RequestMethod.GET)
    public ResponseEntity downfile( String filepath,String filetargetpath){
        try{
            File file = new File(path + filepath);
            InputStream inputStream =  new FileInputStream(file);
            OutputStream outputStream = new FileOutputStream(new File(filetargetpath));
            byte[] b = new byte[1204];
            while( -1 != inputStream.read(b)){
                outputStream.write(b);
            }
        }catch (IOException e){
             e.printStackTrace();
        }

        return ResponseEntity.ok(new ResponseModel("","","文件下载失败！",false));
    }*/
}
