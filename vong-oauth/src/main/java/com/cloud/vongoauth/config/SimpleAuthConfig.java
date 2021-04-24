package com.cloud.vongoauth.config;

//@Configuration
//@EnableAuthorizationServer
//public class SimpleAuthConfig extends AuthorizationServerConfigurerAdapter {
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        //配置网关服务的用户名密码，仅网关服务可作为客户端可访问oauth服务
//        clients
//                .inMemory()
//                .withClient("client")
//                .secret(new BCryptPasswordEncoder().encode("secret"))
//                .authorizedGrantTypes("authorization_code")//授权码模式
//                .scopes("app") //应用的类型
//                .autoApprove(true) //自动审批，不用二次确认
//                .accessTokenValiditySeconds(3600) //有效期1hour
//                .redirectUris("https://localhost:8081/order/c");//code映射路径
//    }
//
//
//}
