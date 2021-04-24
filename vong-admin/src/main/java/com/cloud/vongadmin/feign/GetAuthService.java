package com.cloud.vongadmin.feign;

import com.cloud.vongapi.entity.MyToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequestMapping("/order")
@FeignClient(name = "vong-oauth")
public interface GetAuthService {

    @RequestMapping(value = "/pass")
    public MyToken getPassToken(@RequestParam String username, @RequestParam String password);

}
