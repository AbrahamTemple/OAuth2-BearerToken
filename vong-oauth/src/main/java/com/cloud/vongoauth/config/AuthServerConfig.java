package com.cloud.vongoauth.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.annotation.Resource;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private AuthenticationManager authenticationManager; //密码模式需使用

    // 使用druid连接池。
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource dataSourceConfig()  {
        //return DataSourceBuilder.create().build() 默认jdbc的数据源
        return new DruidDataSource();
    }

    @Bean
    public TokenStore tokenStore() {
        // 保存令牌
        return new JdbcTokenStore(dataSourceConfig());
    }

    @Bean
    public ClientDetailsService jdbcClientDetails() {
        // 获取client子系统数据
        return new JdbcClientDetailsService(dataSourceConfig());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 设置令牌
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)//密码授予的AuthenticationManager
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.GET);//支持Get
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //设置client
        clients.withClientDetails(jdbcClientDetails());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //允许客户端发送表单来进行权限认证来获取令牌
        //只允许认证的客户端，比如网关服务才可以获取和校验token
        security.allowFormAuthenticationForClients()
                .checkTokenAccess("isAuthenticated()")
                .tokenKeyAccess("isAuthenticated()");
    }
}
