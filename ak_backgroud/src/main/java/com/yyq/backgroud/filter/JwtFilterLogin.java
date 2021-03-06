package com.yyq.backgroud.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yyq.backgroud.bean.Role;
import com.yyq.backgroud.bean.User;
import com.yyq.backgroud.model.ResponseModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class JwtFilterLogin extends UsernamePasswordAuthenticationFilter {
    // 自定义签名
    private String signingKey ="lanBridge";
    private AuthenticationManager authenticationManager;
    public JwtFilterLogin(AuthenticationManager authenticationManager){
        this.authenticationManager =authenticationManager;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)  {
        try {

            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ResponseModel responseModel = null;
        if(failed instanceof BadCredentialsException){
            responseModel = new ResponseModel("","error","用户名或者密码错误！",false);
        }else if(failed instanceof LockedException){
            responseModel = new ResponseModel("","error","该账号已被注销！",false);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.write(objectMapper.writeValueAsString(responseModel));
        out.flush();
        out.close();

    }

    /**
     *  登录成功，生成token
     *
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        List<Role> roles = ((User)authResult.getPrincipal()).getRoles();
        User user =(User)authResult.getPrincipal();
        String token ="";
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        // 设置签发时间
        calendar.setTime(new Date());
        // 设置过期时间
        calendar.add(Calendar.HOUR, 24);// 10小时
        Date time = calendar.getTime();
        token =Jwts.builder()
                .setSubject(user.getUsername()+"-"+roles.get(0).getRoleCode())
                .setIssuedAt(now)
                .setExpiration(time)
                .signWith(SignatureAlgorithm.HS512, signingKey)
                .compact();

        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        ResponseModel responseModel =null;
        //  一个用户，对应一个角色
        responseModel = new ResponseModel(user.getRealName()+"_"+ user.getRoles().get(0).getRoleCode(),"","登录成功！",true);
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.write(objectMapper.writeValueAsString(responseModel));
        out.flush();
        out.close();
    }

}
