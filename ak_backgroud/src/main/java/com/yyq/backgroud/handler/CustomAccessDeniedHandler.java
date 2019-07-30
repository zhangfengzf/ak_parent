package com.yyq.backgroud.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yyq.backgroud.model.ResponseModel;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *  权限处理器
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseModel responseModel = new ResponseModel("","","权限不足！",false);
        PrintWriter printWriter =  httpServletResponse.getWriter();
        printWriter.write(objectMapper.writeValueAsString(responseModel));
        printWriter.flush();
        printWriter.close();

    }
}
