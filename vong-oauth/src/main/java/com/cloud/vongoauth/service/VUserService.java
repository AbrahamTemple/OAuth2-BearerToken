package com.cloud.vongoauth.service;

import com.cloud.vongoauth.entity.vUser;

public interface VUserService {
    public vUser getByUserName(String username);
    public String getClientId(String username, String password);
}
