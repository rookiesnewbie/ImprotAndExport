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
@ApiModel(value="Record对象", description="")
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.AUTO)
    private Long recordId;

    @ApiModelProperty(value = "样品编号")
    private String sampleNo;

    @ApiModelProperty(value = "调查船")
    private String shipName;

    @ApiModelProperty(value = "航次")
    private String voyageNo;

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
