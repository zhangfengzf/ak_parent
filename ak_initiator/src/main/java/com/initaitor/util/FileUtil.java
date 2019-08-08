package com.initaitor.util;

import lombok.Cleanup;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName: FileUtil
 * @Description: 文件生成工具类
 * @Author 张锋
 * @Date 2019/7/31 10:11
 * @Version 1.0
 */
public class FileUtil {

    public static void createFile(String filePath,String fileContent) throws IOException {
        @Cleanup FileWriter fw = new FileWriter(filePath);
        @Cleanup PrintWriter pw = new PrintWriter(fw);
        fw.write(fileContent);
        pw.println();
    }

}
