package com.spring.vongbase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spring.vongbase.entity.vUser;
import com.spring.vongbase.mapper.VUserMapper;
import com.spring.vongbase.service.VUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VUserServiceImpl implements VUserService {

    @Resource
    private VUserMapper vUserMapper;

    @Override
    public String getClientId(String username, String password) {
        QueryWrapper<vUser> userInfo = new QueryWrapper<vUser>()
                .eq("username", username).eq("password",password);
        List<vUser> vUsers = vUserMapper.selectList(userInfo);
        if(vUsers.size()>0){
            return vUsers.get(0).getClientId();
        } return null;
    }
}
