package com.bojin.entity.vo.response;

import com.bojin.entity.Record;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 响应相关实体
 */
@Data
public class RecordRepsVO extends Record implements Serializable {


    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long seatId;

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
}
