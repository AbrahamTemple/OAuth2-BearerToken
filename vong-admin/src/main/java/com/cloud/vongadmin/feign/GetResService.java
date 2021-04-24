package com.cloud.vongadmin.feign;

import com.cloud.vongapi.http.ResponseResult;
import com.cloud.vongapi.table.vongCover;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Service
@FeignClient(name = "vong-base")
public interface GetResService {
    @GetMapping("/album")
    public ResponseResult<List<vongCover>> pages(@RequestHeader(value = HttpHeaders.AUTHORIZATION,required = false) String authentication);
}
