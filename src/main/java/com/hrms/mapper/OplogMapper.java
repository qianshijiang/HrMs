package com.hrms.mapper;

import com.hrms.common.base.BaseMapper;
import com.hrms.entity.Oplog;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OplogMapper extends BaseMapper<Oplog,String> {

  List<Oplog> getOplogBydate(@Param("startime") String startime,@Param("endtime") String endtime);

  List<Oplog> getOplogByPage();

}