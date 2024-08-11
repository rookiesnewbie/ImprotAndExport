package com.bojin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bojin.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bojin.entity.vo.query.RecordQueryVo;
import com.bojin.entity.vo.request.RecordAddReq;
import com.bojin.entity.vo.response.RecordRepsVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 */
public interface RecordService extends IService<Record> {

    void importRecord(MultipartFile file) throws IOException;

    Map<String, Object> selectRecordPage(Page<Record> page, RecordQueryVo recordQueryVo);

    boolean batchRemoveRecord(List<Long> ids);

    boolean removeRecordById(Long id);

    RecordRepsVO getRecordById(Long id);

    boolean addRecord(RecordAddReq recordAddReq);

    boolean updateRecord(RecordAddReq recordAddReq);

    void exportRecordToExcel(HttpServletResponse servletResponse) throws IOException;
}
