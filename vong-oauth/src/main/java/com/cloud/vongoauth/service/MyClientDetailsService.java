package com.cloud.vongoauth.service;


import com.cloud.vongoauth.entity.oauthClientDetails;

public interface MyClientDetailsService {
    public oauthClientDetails getAllByClientId(String clientId);
}
