package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePass {
    private String username;
    private String password;
    private String currentPassword;
    private String confirmPassword;
}
