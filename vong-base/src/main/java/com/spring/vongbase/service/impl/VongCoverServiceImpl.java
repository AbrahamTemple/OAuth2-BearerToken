package com.spring.vongbase.service.impl;

import com.spring.vongbase.entity.vongCover;
import com.spring.vongbase.mapper.VongCoverMapper;
import com.spring.vongbase.service.VongCoverService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VongCoverServiceImpl implements VongCoverService {

    @Resource
    private VongCoverMapper vongCoverMapper;

    @Override
    public List<vongCover> selectAll() {
        return vongCoverMapper.selectList(null);
    }
}
