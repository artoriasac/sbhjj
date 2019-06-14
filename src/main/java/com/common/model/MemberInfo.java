package com.common.model;

import lombok.Data;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 9:50 2018/7/31 0031
 * Modeified By:
 */
@Data
public class MemberInfo {
    private Integer id;

    private String account;

    private String nickname;

    private String headUrl;

    private String gender;

    private String mark;
}
