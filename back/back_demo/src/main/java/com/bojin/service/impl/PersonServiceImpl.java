package com.bojin.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.bojin.common.exception.CustomException;
import com.bojin.common.result.ResultCodeEnum;
import com.bojin.entity.Person;
import com.bojin.entity.vo.excel.PersonExportExcel;
import com.bojin.entity.vo.excel.PersonImportExcel;
import com.bojin.mapper.PersonMapper;
import com.bojin.service.PersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 人物表 服务实现类
 * </p>
 *
 * @author LiTeng
 * @since 2024-08-11
 */
@Service
@Slf4j
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

    @Override
    public void importPerson(MultipartFile file) throws IOException {
        if (file == null){
            throw new CustomException(ResultCodeEnum.UPLOAD_FILE_NOT_BLANK);
        }

        EasyExcel.read(file.getInputStream(), PersonImportExcel.class, new PageReadListener<PersonImportExcel>(dataList -> {

            for (PersonImportExcel personImportExcel : dataList) {
                log.info("读取到一条数据{}", personImportExcel);

            }

            List<Person> personList = new ArrayList<>();

            dataList.forEach(personImportExcel -> {
                if(null != personImportExcel){
                    Person person = new Person();
                    BeanUtils.copyProperties(personImportExcel,person);
                    personList.add(person);
                }
            });

            log.info("personList={}",personList);
            this.saveOrUpdateBatch(personList);

        })).sheet().doRead();
    }

    @Override
    public void exportPerson(HttpServletResponse response) throws IOException {

        //查询所有的人物信息
        List<Person> personList = this.list();


        List<PersonExportExcel> recordRepsVOList = new ArrayList<>();
        for (Person person : personList) {
            PersonExportExcel personExportExcel = new PersonExportExcel();
            BeanUtils.copyProperties(person,personExportExcel);
            recordRepsVOList.add(personExportExcel);
        }

        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("人物信息.xlsx", "UTF-8"));
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");

        EasyExcel.write(response.getOutputStream(), PersonExportExcel.class).autoCloseStream(Boolean.FALSE).sheet("sheet1")
                .doWrite(recordRepsVOList);
    }
}
