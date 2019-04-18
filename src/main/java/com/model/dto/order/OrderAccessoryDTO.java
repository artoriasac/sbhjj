package com.model.dto.order;

import io.swagger.annotations.ApiModelProperty;

public class OrderAccessoryDTO {
    @ApiModelProperty("附件url")
    private String url;
    @ApiModelProperty("附件名")
    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
