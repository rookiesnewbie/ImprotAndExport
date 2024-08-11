package com.bojin.entity.vo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 导出相关实体
 */
@Data
public class InventoryRecordExportExcel {
    @ExcelProperty(value = {"出库单","序号"},index = 0)
    private Long recordId;

    @ExcelProperty(value = {"出库单","样品编号"},index = 1)
    private String sampleNo;

    @ExcelProperty(value = {"出库单","调查船"},index = 2)
    private String shipName;

    @ExcelProperty(value = {"出库单","航次"},index = 3)
    private String voyageNo;
    @ExcelProperty(value = {"出库单","详细位置", "海域"},index = 4)
    private String seaArea;

    @ExcelProperty(value = {"出库单","详细位置","站位"},index = 5)
    private String station;

    @ExcelProperty(value = {"出库单","详细位置","X坐标"},index = 6)
    private String xCoordinate;

    @ExcelProperty(value = {"出库单","详细位置","Y坐标"},index = 7)
    private String yCoordinate;

    @ExcelProperty(value = {"出库单","详细位置","详细地址"},index = 8)
    private String detailAddress;

    @ExcelProperty(value = {"出库单","结束深度（m）"},index = 9)
    private Double endDepth;

    @ExcelProperty(value = {"出库单","心长（m）"},index = 10)
    private Double heartLength;

    @ExcelProperty(value = {"出库单","存放位置"},index = 11)
    private String storageLocation;

    @ExcelProperty(value = {"出库单","保存状况"},index = 12)
    private Double preservationCondition;

    @ExcelProperty(value = {"出库单","备注"},index = 13)
    private String remarks;



}
