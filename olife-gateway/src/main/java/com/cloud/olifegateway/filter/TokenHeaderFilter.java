package com.cloud.olifegateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.4.10
 * @GitHub https://github.com/AbrahamTemple/
 * @description: 网关转发带上token请求头
 */
@Component
public class TokenHeaderFilter implements GlobalFilter, Ordered {

    /**
     * 如果没有access_token参数将会报406错误
     * 将请求参数添加到请求头
     * @param exchange 服务器交换机
     * @param chain 网关过滤器链接节点
     * @return 链接完成的过滤器
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String token = exchange.getRequest().getQueryParams().getFirst("access_token");//队列参数第一个
        if(token == null){
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);//回应一个不被接收的状态码：406
            return exchange.getResponse().setComplete();//完成并退出
        }

        ServerHttpRequest host = exchange.getRequest().mutate().headers(httpHeaders -> {
            httpHeaders.add(HttpHeaders.AUTHORIZATION,token);
        }).build();
        ServerWebExchange build = exchange.mutate().request(host).build();
        return chain.filter(build);//这个请求通过
    }

    /**
     * 获取此对象的过滤顺序
     * @return 订单值
     */
    @Override
    public int getOrder() {
        return 0; //代表加载过滤器的顺序，这个数字越小优先级越高
    }
}
