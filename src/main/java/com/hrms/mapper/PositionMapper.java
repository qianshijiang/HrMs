package com.hrms.mapper;

import com.hrms.common.base.BaseMapper;
import com.hrms.entity.Position;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by sang on 2018/1/10.
 */
public interface PositionMapper extends BaseMapper<Position,String>{

    int addPos(@Param("pos") Position pos);

    Position getPosByName(String name);

    List<Position> getAllPos();

    int deletePosById(@Param("pids") String[] pids);

    int updatePosById(@Param("pos") Position position);
}
