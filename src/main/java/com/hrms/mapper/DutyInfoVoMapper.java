package com.hrms.mapper;

import com.hrms.common.base.BaseMapper;
import com.hrms.entity.vo.DutyInfoVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Created by QSJ on 2019/7/18.
 */
public interface DutyInfoVoMapper extends BaseMapper <DutyInfoVo,String>{

   List<DutyInfoVo> findListByPage(@Param("dutyTime") String dutyTime,@Param("empName") String empName,@Param("workId") String workId);

}
