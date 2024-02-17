package com.aj.blog.auth;

import lombok.Data;

@Data
public class RegisterReq {

    private String handleName;
    private String email;
    private String password;

}
