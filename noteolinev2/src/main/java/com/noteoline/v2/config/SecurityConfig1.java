package com.noteoline.v2.config;


import com.noteoline.v2.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.security.config.http.MatcherType.ant;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig1 extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService userDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailService).passwordEncoder(password());
    }







    @Override
    protected void configure(HttpSecurity http) throws Exception{


        http.formLogin()
                .loginPage("/login")
                /*.loginProcessingUrl("/user/login")*/
                .defaultSuccessUrl("/index").permitAll()
                .and().authorizeRequests()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/index","/" ).hasAnyAuthority("admins","common")
//                    .antMatchers("/in/sa*","in/up*","/in/del/*").hasAuthority("admins")
//                    .antMatchers("/in/a","/s/a","/p/a").hasAuthority("common")
                .and().csrf().disable();
                /*.usernameParameter("username").passwordParameter("password")*/
                /*.failureUrl("/login?error");*/
        http.headers().frameOptions().sameOrigin();


        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");


    }


    @Bean
    PasswordEncoder password(){
    return new BCryptPasswordEncoder();
}
}
