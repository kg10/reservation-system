package com.project.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.Service.AdministratorService;
import com.project.Service.AdministratorServiceImpl;
import com.project.Service.ClientDetailService;
import com.project.Service.ClientService;
import com.project.Service.ClientServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ClientDetailService clientDetailService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(clientDetailService).passwordEncoder(passwordencoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.antMatchers("/reg/**").denyAll()
                //.antMatchers("/reg/findAllPersonnel").hasAnyAuthority("ADMIN")
                //.antMatchers("/reg/findAllServices").hasAnyAuthority("USER")
                .antMatchers("/login","/reg/register").permitAll()
                .and().httpBasic()
                .and().csrf().disable();
    }

    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordencoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AdministratorService administratorService() {
        return new AdministratorServiceImpl();
    }

    @Bean
    public ClientService clientService() {
        return new ClientServiceImpl();
    }


}
