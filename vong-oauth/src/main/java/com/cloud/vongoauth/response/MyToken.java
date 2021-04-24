package com.cloud.vongoauth.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyToken implements Serializable {
    private String access_token;
    private String token_type;
    private Integer expires_in;
    private String scope;
}
