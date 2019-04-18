package com.model.vo.file;

import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;

public class TokenVO extends AssumeRoleResponse {
    private String bucket;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}
