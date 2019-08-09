package com.yyq.backgroud.config;

import com.yyq.backgroud.filter.JwtAuthorizationFilter;
import com.yyq.backgroud.filter.JwtFilterLogin;
import com.yyq.backgroud.filter.MyAccessDecisionManager;
import com.yyq.backgroud.filter.MySecurityMetadataSource;
import com.yyq.backgroud.handler.CustomAccessDeniedHandler;
import com.yyq.backgroud.handler.CustomAuthenticationEntryPoint;
import com.yyq.backgroud.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private MyAccessDecisionManager accessDecisionManager;
   @Autowired
    private MySecurityMetadataSource securityMetadataSource;
   @Autowired
   private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
   @Autowired
   private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
             http.cors().and().authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll()/*.antMatchers("/").permitAll()*/.anyRequest().authenticated()
                    .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                        @Override
                        public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                            object.setSecurityMetadataSource(securityMetadataSource);
                            object.setAccessDecisionManager(accessDecisionManager);
                            return  object;
                        }
                    })
                     .and().
                     addFilter(new JwtFilterLogin(authenticationManager()))
                     .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                    .formLogin().loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/index").permitAll()
                     .and().exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint).accessDeniedHandler(customAccessDeniedHandler).and()
                    .logout().permitAll().and().csrf().disable();
            ;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
