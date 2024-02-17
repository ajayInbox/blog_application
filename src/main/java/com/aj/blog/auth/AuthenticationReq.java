package com.aj.blog.auth;

import lombok.Data;

@Data
public class AuthenticationReq {

    private String email;
    private String password;

}
