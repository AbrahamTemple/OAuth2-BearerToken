package com.cloud.vongoauth.service.impl;

import com.cloud.vongoauth.entity.vPermission;
import com.cloud.vongoauth.mapper.VPermissionMapper;
import com.cloud.vongoauth.service.VPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VPermissionServiceImpl implements VPermissionService {

    @Resource
    private VPermissionMapper vPermissionMapper;

    public List<vPermission> selectByUserId(Long id) {
        List<vPermission> vPermissions = vPermissionMapper.selectByLeftUserId(id);
        if(vPermissions.size()>0){
            return vPermissions;
        } return null;
    }
}
