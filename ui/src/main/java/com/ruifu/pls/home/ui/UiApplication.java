package com.ruifu.pls.home.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
@Controller
@EnableZuulProxy
public class UiApplication {

    //https://spring.io/guides/tutorials/spring-security-and-angular-js/
    //https://github.com/spring-guides/tut-spring-security-and-angular-js/tree/angularjs/single

    @RequestMapping("/user")
    @ResponseBody
    public Principal user(Principal user){
        return user;
    }

    @RequestMapping("/token")
    @ResponseBody
    public Map<String,String> token(HttpSession session) {
        return Collections.singletonMap("token", session.getId());
    }

    @GetMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        return "forward:/";
    }

    public static void main(String[] args) {
        SpringApplication.run(UiApplication.class, args);
    }


    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception{
            http.httpBasic().and()
                    .authorizeRequests()
                    .antMatchers("/index.html","login.html","/home.html","/favicon.ico", "/app/**", "/css/**", "/login","/js/**")
                    .permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .csrf()
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        }


    }

}
