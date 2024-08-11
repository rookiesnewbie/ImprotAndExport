package com.bojin.service;

import com.bojin.entity.Person;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * <p>
 * 人物表 服务类
 * </p>
 *
 * @author LiTeng
 * @since 2024-08-11
 */
public interface PersonService extends IService<Person> {

    void importPerson(MultipartFile file) throws IOException;

    void exportPerson(HttpServletResponse response) throws IOException;
}
