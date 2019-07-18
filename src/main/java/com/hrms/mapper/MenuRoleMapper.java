package com.hrms.mapper;

import com.hrms.common.base.BaseMapper;
import com.hrms.entity.MenuRole;
import org.apache.ibatis.annotations.Param;

/**
 * Created by sang on 2018/1/2.
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole,String>{
    int deleteMenuByRid(@Param("rid") Long rid);

    int addMenu(@Param("rid") Long rid, @Param("mids") Long[] mids);
}
