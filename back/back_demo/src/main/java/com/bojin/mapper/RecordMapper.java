package com.bojin.mapper;

import com.bojin.entity.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 */
public interface RecordMapper extends BaseMapper<Record> {

    Long getMaxIndex();

}
