package com.bojin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bojin.common.exception.CustomException;
import com.bojin.common.result.ResultCodeEnum;
import com.bojin.entity.Record;
import com.bojin.entity.Seat;
import com.bojin.entity.vo.excel.InventoryRecordExportExcel;
import com.bojin.entity.vo.excel.InventoryRecordImportExcel;
import com.bojin.entity.vo.query.RecordQueryVo;
import com.bojin.entity.vo.request.RecordAddReq;
import com.bojin.entity.vo.response.RecordRepsVO;
import com.bojin.listener.MoreSheetListener;
import com.bojin.mapper.RecordMapper;
import com.bojin.service.RecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bojin.service.SeatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LiTeng
 * @since 2024-08-09
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {

    /**
     *
     */
    private final SeatService seatService;


    /**
     * 导入
     *
     * @param file
     * @throws IOException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void importRecord(MultipartFile file) throws IOException {
        if (file.isEmpty()){
            throw new CustomException(ResultCodeEnum.UPLOAD_FILE_NOT_BLANK);
        }

        EasyExcel.read(file.getInputStream(), InventoryRecordImportExcel.class, new PageReadListener<InventoryRecordImportExcel>(dataList -> {

            for (InventoryRecordImportExcel recordImportExcel : dataList) {
                log.info("读取到一条数据{}", recordImportExcel);

            }

            List<Record> recordList = new ArrayList<>();
            List<Seat> seatList = new ArrayList<>();

            dataList.forEach(recordImportExcel -> {
                if(null != recordImportExcel){
                    Record record = new Record();
                    Seat seat = new Seat();

                    if (recordImportExcel.getRecordId() != null){
                        BeanUtils.copyProperties(recordImportExcel,record);
                        recordList.add(record);
                        BeanUtils.copyProperties(recordImportExcel,seat);
                        seat.setSeatId(record.getRecordId());
                        seatList.add(seat);
                    }


                }
            });

            log.info("recordList={}",recordList);
            log.info("seatList={}",seatList);
            this.saveOrUpdateBatch(recordList);

            seatService.saveOrUpdateBatch(seatList);

        })).sheet().doRead();

    }

    /**
     * 条件分页查询
     *
     * @param recordQueryVo 查询条件
     * @param page
     */
    @Override
    public Map<String, Object> selectRecordPage(Page<Record> page, RecordQueryVo recordQueryVo) {

        LambdaQueryWrapper<Record> wrapper = new LambdaQueryWrapper<>();
        if (!StrUtil.isEmpty(recordQueryVo.getShipName())){
            wrapper.like(Record::getShipName,recordQueryVo.getShipName());
        }
        if (!StrUtil.isEmpty(recordQueryVo.getVoyageNo())){
            wrapper.like(Record::getVoyageNo,recordQueryVo.getVoyageNo());
        }
        wrapper.orderByAsc(Record::getRecordId);

        Page<Record> recordPage = baseMapper.selectPage(page, wrapper);
        List<Record> records = recordPage.getRecords();

        //获取全部的record的id
        List<Long> recordIdList = records.stream().map(record -> record.getRecordId()).collect(Collectors.toList());

        LambdaQueryWrapper<Seat> queryWrapper = new LambdaQueryWrapper<>();

        if (!StrUtil.isEmpty(recordQueryVo.getSeaArea())){
            queryWrapper.like(Seat::getSeaArea,recordQueryVo.getSeaArea());
        }

        if (recordIdList.size() > 0) {
            queryWrapper.in(Seat::getRecordId, recordIdList);
        }
        List<Seat> seatList = seatService.getBaseMapper().selectList(queryWrapper);

        //封装要返回给前端的数据
        //注意：BeanUtils.copyProperties()在做属性复制时null也会复制，在stream流中慎用
        List<RecordRepsVO> recordRepsVOList = new ArrayList<>();
        for (Record record : records) {
            RecordRepsVO recordRepsVO = null;
            for (Seat seat : seatList) {
                if (record.getRecordId().equals(seat.getRecordId())) {
                    recordRepsVO = new RecordRepsVO();
                    BeanUtils.copyProperties(record, recordRepsVO);
                    recordRepsVO.setSeatId(seat.getSeatId());
                    recordRepsVO.setSeaArea(seat.getSeaArea());
                    recordRepsVO.setStation(seat.getStation());
                    recordRepsVO.setXCoordinate(seat.getXCoordinate());
                    recordRepsVO.setYCoordinate(seat.getYCoordinate());
                    recordRepsVO.setDetailAddress(seat.getDetailAddress());
                    recordRepsVOList.add(recordRepsVO);
                }
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("records",recordRepsVOList);
        map.put("total",recordPage.getTotal());

        return map;
    }

    /**
     * 批量删除
     *
     * @param ids 主键
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchRemoveRecord(List<Long> ids) {

        if (ids.size() > 0){
            baseMapper.deleteBatchIds(ids);

            LambdaQueryWrapper<Seat> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(Seat::getRecordId,ids);

            seatService.getBaseMapper().delete(wrapper);

            return true;
        }


        return false;
    }

    /**
     * 单个删除
     *
     * @param id 主键
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeRecordById(Long id) {
        if (id != null){
            baseMapper.deleteById(id);

            LambdaQueryWrapper<Seat> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Seat::getRecordId,id);

            seatService.getBaseMapper().delete(wrapper);

            return true;
        }

        return false;
    }

    /**
     * 根据id查询样本数据信息
     *
     * @param id 主键
     * @param id
     */
    @Override
    public RecordRepsVO getRecordById(Long id) {

        RecordRepsVO recordRepsVO = new RecordRepsVO();
        if (id != null){
            Record record = baseMapper.selectById(id);
            LambdaQueryWrapper<Seat> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Seat::getRecordId,id);
            Seat seat = seatService.getOne(wrapper);

            BeanUtils.copyProperties(record,recordRepsVO);
            BeanUtils.copyProperties(seat,recordRepsVO);
        }
        return recordRepsVO;
    }


    /**
     * 添加样本数据
     *
     * @param recordAddReq
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addRecord(RecordAddReq recordAddReq) {
        Long maxIndex = baseMapper.getMaxIndex();

        if (recordAddReq != null){
            Record record = new Record();
            Seat seat = new Seat();
            BeanUtils.copyProperties(recordAddReq,record);
            BeanUtils.copyProperties(recordAddReq,seat);
            if (maxIndex == null){
                seat.setRecordId(1L);
            }else {
                seat.setRecordId(maxIndex + 1L);
            }

            baseMapper.insert(record);
            seatService.save(seat);
            return true;
        }
        return false;
    }


    /**
     * 修改样本数据
     *
     * @param recordAddReq
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRecord(RecordAddReq recordAddReq) {

        if (recordAddReq != null){
            Record record = new Record();
            Seat seat = new Seat();
            BeanUtils.copyProperties(recordAddReq,record);
            BeanUtils.copyProperties(recordAddReq,seat);
            baseMapper.updateById(record);
            seatService.updateById(seat);
            return true;
        }

        return false;
    }

    /**
     * 导出样本数据
     *
     * @param servletResponse
     */
    @Override
    public void exportRecordToExcel(HttpServletResponse servletResponse) throws IOException {


        //查询所有的记录信息
        List<Record> recordList = this.list();


        //查询所有的位置信息
        List<Seat> seatList = seatService.list();

        List<InventoryRecordExportExcel> recordRepsVOList = new ArrayList<>();
        for (Record record : recordList) {
            InventoryRecordExportExcel recordExportExcel = null;
            for (Seat seat : seatList) {
                if (record.getRecordId().equals(seat.getRecordId())) {
                    recordExportExcel = new InventoryRecordExportExcel();
                    BeanUtils.copyProperties(record, recordExportExcel);
                    recordExportExcel.setSeaArea(seat.getSeaArea());
                    recordExportExcel.setStation(seat.getStation());
                    recordExportExcel.setXCoordinate(seat.getXCoordinate());
                    recordExportExcel.setYCoordinate(seat.getYCoordinate());
                    recordExportExcel.setDetailAddress(seat.getDetailAddress());
                    recordRepsVOList.add(recordExportExcel);
                }
            }
        }

        servletResponse.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("样本数据.xlsx", "UTF-8"));
        servletResponse.setContentType("application/vnd.ms-excel;charset=UTF-8");

        EasyExcel.write(servletResponse.getOutputStream(), InventoryRecordExportExcel.class).autoCloseStream(Boolean.FALSE).sheet("sheet1")
                .doWrite(recordRepsVOList);

    }

    /**
     * 将excel表的所有sheet的数据存入数据库
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importMoreSheetRecord(MultipartFile file) throws IOException {
        MoreSheetListener listener = new MoreSheetListener();

        ExcelReaderBuilder excelReaderBuilder = EasyExcel.read(file.getInputStream(), InventoryRecordImportExcel.class, listener);
        excelReaderBuilder.doReadAll();

        // 获取所有 sheet 的数据
        List<InventoryRecordImportExcel> allData = listener.getDataList();
        //allData.forEach(System.out::println);


        List<Record> recordList = new ArrayList<>();
        List<Seat> seatList = new ArrayList<>();

        allData.forEach(recordImportExcel ->{
            if(null != recordImportExcel){
                Record record = new Record();
                Seat seat = new Seat();
                if (recordImportExcel.getRecordId() != null){
                    BeanUtils.copyProperties(recordImportExcel,record);
                    recordList.add(record);
                    BeanUtils.copyProperties(recordImportExcel,seat);
                    seat.setSeatId(record.getRecordId());
                    seatList.add(seat);
                }
            }
        });

        this.saveOrUpdateBatch(recordList);
        seatService.saveOrUpdateBatch(seatList);

    }
}
