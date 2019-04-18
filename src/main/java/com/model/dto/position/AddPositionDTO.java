package com.model.dto.position;

import io.swagger.annotations.ApiModelProperty;

public class AddPositionDTO {
    private String content;
    @ApiModelProperty("熟练等级id")
    private Integer rank;
    @ApiModelProperty("权重  可以是小数")
    private Double weight;
    @ApiModelProperty("职务分类id")
    private Integer classify;

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
