package com.bojin.entity.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RecordAddReq{

    @ApiModelProperty(value = "主键")
    private Long recordId;

    @ApiModelProperty(value = "主键")
    private Long seatId;

    @ApiModelProperty(value = "样品编号")
    private String sampleNo;

    @ApiModelProperty(value = "调查船")
    private String shipName;

    @ApiModelProperty(value = "航次")
    private String voyageNo;

    @ApiModelProperty(value = "海域")
    private String seaArea;

    @ApiModelProperty(value = "站位")
    private String station;

    @ApiModelProperty(value = "X坐标")
    private String xCoordinate;

    @ApiModelProperty(value = "Y坐标")
    private String yCoordinate;

    @ApiModelProperty(value = "详细地址")
    private String detailAddress;

    @ApiModelProperty(value = "结束深度")
    private Double endDepth;

    @ApiModelProperty(value = "心长")
    private Double heartLength;

    @ApiModelProperty(value = "存放位置")
    private String storageLocation;

    @ApiModelProperty(value = "保存状况")
    private Double preservationCondition;

    @ApiModelProperty(value = "备注")
    private String remark;
}
