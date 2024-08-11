package com.bojin.entity.vo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.bojin.entity.SexConverter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 */
@Data
public class PersonImportExcel {


    @ExcelProperty(value = "序号",index = 0)
    private Long id;

    @ExcelProperty(value = "姓名",index = 1)
    private String name;

    @ExcelProperty(value = "性别",index = 2, converter = SexConverter.class)
    private Integer sex;

    @ExcelProperty(value = "年龄",index = 3)
    private Integer age;

    @ExcelProperty(value = "时代",index = 4)
    private String era;

    @ExcelProperty(value = "电话",index = 5)
    private String phone;

    @ExcelProperty(value = "职业",index = 6)
    private String career ;

}
