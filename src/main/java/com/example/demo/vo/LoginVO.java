package com.example.demo.vo;

import com.example.demo.validator.IsMobile;
import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginVO {

    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    private String password;

}
