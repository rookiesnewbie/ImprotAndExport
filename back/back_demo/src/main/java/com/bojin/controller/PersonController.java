package com.bojin.controller;


import com.bojin.common.result.Result;
import com.bojin.service.PersonService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 人物表 前端控制器
 * </p>
 *
 * @author LiTeng
 * @since 2024-08-11
 */
@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    @ApiOperation("导入样本数据")
    @PostMapping("/import")
    public Result importPerson(MultipartFile file) throws IOException {
        personService.importPerson(file);
        return Result.succeed(null);
    }


    @ApiOperation("导出样本数据")
    @GetMapping("/export")
    public Result exportPerson(HttpServletResponse response) throws IOException {
        personService.exportPerson(response);
        return Result.succeed(null);
    }
}
