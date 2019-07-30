package com.yyq.backgroud.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yyq.backgroud.model.ResponseModel;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomAuthenticationEntryPoint  implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ObjectMapper o = new ObjectMapper();
        ResponseModel responseModel = new ResponseModel("","error",e.getMessage(),false);
        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.write(o.writeValueAsString(responseModel));
        printWriter.flush();
        printWriter.close();

    }
}
