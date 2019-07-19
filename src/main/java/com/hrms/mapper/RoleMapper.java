package com.hrms.mapper;

import com.hrms.common.base.BaseMapper;
import com.hrms.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by sang on 2018/1/1.
 */
public interface RoleMapper extends BaseMapper<Role,String>{
    List<Role> roles();

    int addNewRole(@Param("role") String role, @Param("roleZh") String roleZh);

    int deleteRoleById(Integer rid);
}
