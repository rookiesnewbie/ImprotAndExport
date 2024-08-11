package com.bojin.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Seat对象", description="")
public class Seat implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.AUTO)
    private Long seatId;

    @ApiModelProperty(value = "记录id")
    private Long recordId;

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
