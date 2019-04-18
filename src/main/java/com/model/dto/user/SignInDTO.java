package com.model.dto.user;

import io.swagger.annotations.ApiModelProperty;

public class SignInDTO {
    @ApiModelProperty("账号")
    private String account;
    @ApiModelProperty("密码")
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
