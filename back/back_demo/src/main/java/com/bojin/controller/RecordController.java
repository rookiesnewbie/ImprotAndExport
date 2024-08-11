package com.bojin.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bojin.common.result.Result;
import com.bojin.entity.Record;
import com.bojin.entity.vo.query.RecordQueryVo;
import com.bojin.entity.vo.request.RecordAddReq;
import com.bojin.entity.vo.response.RecordRepsVO;
import com.bojin.service.RecordService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/record")
public class RecordController {

    private final RecordService recordService;

    @ApiOperation("导入样本数据")
    @PostMapping("/import")
    public Result importRecord(MultipartFile file) throws IOException {
        recordService.importRecord(file);
        return Result.succeed(null);
    }


    @ApiOperation("导入样本数据")
    @PostMapping("/importMore")
    public Result importMore(MultipartFile file) throws IOException {
        recordService.importMoreSheetRecord(file);
        return Result.succeed(null);
    }


    @ApiOperation("条件分页查询样本数据")
    @GetMapping("{current}/{limit}")
    public Result save(@PathVariable("current")Long current,
                       @PathVariable("limit") Long limit,
                       RecordQueryVo recordQueryVo){
        Page<Record> page = new Page<>(current, limit);
        Map<String,Object> map = recordService.selectRecordPage(page,recordQueryVo);
        return Result.succeed(map);

    }

    @ApiOperation("添加样本数据")
    @PostMapping("save")
    public Result addRecord(@RequestBody RecordAddReq recordAddReq){

        boolean save = recordService.addRecord(recordAddReq);
        return Result.succeed(save);

    }

    @ApiOperation("根据id查询样本数据")
    @GetMapping("get/{id}")
    public Result getRecordById(@PathVariable Long  id){
        RecordRepsVO RecordRespVo = recordService.getRecordById(id);
        return Result.succeed(RecordRespVo);

    }
    @ApiOperation("修改样本数据")
    @PutMapping("update")
    public Result update(@RequestBody RecordAddReq recordAddReq){
        boolean update = recordService.updateRecord(recordAddReq);
        return Result.succeed(update);

    }

    @ApiOperation("根据id删除样本数据")
    @DeleteMapping ("remove/{id}")
    public Result remove(@PathVariable Long id){
        boolean remove = recordService.removeRecordById(id);
        return Result.succeed(remove);

    }

    @ApiOperation("批量删除样本数据")
    @DeleteMapping ("batchRemove")
    public Result batchRemoveRecord(@RequestBody List<Long> ids){
        boolean removeByIds = recordService.batchRemoveRecord(ids);
        return Result.succeed(removeByIds);

    }

    @ApiOperation("导出样本数据")
    @GetMapping("/export")
    public Result exportRecord(HttpServletResponse servletResponse) throws IOException {
        recordService.exportRecordToExcel(servletResponse);
        return Result.succeed(null);
    }

}
