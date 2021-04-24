package com.cloud.vongadmin.controller;

import com.cloud.vongadmin.feign.GetAuthService;
import com.cloud.vongadmin.feign.GetResService;
import com.cloud.vongapi.entity.MyToken;
import com.cloud.vongapi.http.ResponseResult;
import com.cloud.vongapi.table.vongCover;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class MainOauthController {

    @Resource
    GetAuthService getAuthService;

    @Resource
    GetResService getResService;


    @RequestMapping("/auth")
    public MyToken getPassToken(@RequestParam String username, @RequestParam String password){
        return getAuthService.getPassToken(username,password);
    }

    @RequestMapping("/res")
    public ResponseResult<List<vongCover>> getPassToken(@RequestHeader(value = HttpHeaders.AUTHORIZATION,required = false) String authentication){
        return getResService.pages(authentication);
    }
}
