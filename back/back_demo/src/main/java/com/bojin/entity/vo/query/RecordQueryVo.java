package com.bojin.entity.vo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RecordQueryVo {
    @ApiModelProperty(value = "调查船")
    private String shipName;

    @ApiModelProperty(value = "航次")
    private String voyageNo;

    @ApiModelProperty(value = "海域")
    private String seaArea;
}
