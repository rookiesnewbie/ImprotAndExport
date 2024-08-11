package com.bojin.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.bojin.entity.vo.excel.InventoryRecordImportExcel;

import java.util.ArrayList;
import java.util.List;

public class MoreSheetListener  implements ReadListener<InventoryRecordImportExcel> {
    private List<InventoryRecordImportExcel> dataList = new ArrayList<>();

    @Override
    public void invoke(InventoryRecordImportExcel data, AnalysisContext context) {
        dataList.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 所有数据解析完成后的操作
    }

    public List<InventoryRecordImportExcel> getDataList() {
        return dataList;
    }
}
