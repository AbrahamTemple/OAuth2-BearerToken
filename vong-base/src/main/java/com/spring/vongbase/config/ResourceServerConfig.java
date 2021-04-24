package com.spring.vongbase.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true,jsr250Enabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String DEMO_RESOURCE_ID = "resid";

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable().
                authorizeRequests().anyRequest().authenticated();
//        http.authorizeRequests()
//                .antMatchers("/","/pass").hasAuthority("VONG_RES");
        http.cors(); //开启跨域资源共享
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        //不配资源id就会报Invalid token does not contain resource id (oauth2-resource)
        resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
    }
}
