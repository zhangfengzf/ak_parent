package com.yyq.wordtrans.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class WriteFileTask {

    @Async
    public  void wirteWordTask(String userId,String soundCarName,String message) {

        String fileName = "cn".equals(soundCarName)? "中文":"英文";
        String path = "D:\\ak\\record\\"+userId+"\\"+"文字"+"\\"+fileName+".doc";
        File file = new File(path);
        if(!file.exists()){
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(message);
            bw.flush();
            bw.close();
            fw.close();
        }catch (IOException e){
          e.printStackTrace();
        }
    }

}
