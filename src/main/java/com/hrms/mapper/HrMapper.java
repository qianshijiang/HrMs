package com.hrms.mapper;

import com.hrms.common.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.hrms.entity.Hr;
import com.hrms.entity.Role;

import java.util.List;

/**
 * Created by sang on 2017/12/28.
 */
public interface HrMapper extends BaseMapper<Hr,String>{
    Hr loadUserByUsername(String username);

    List<Role> getRolesByHrId(Long id);

    int hrReg(@Param("username") String username, @Param("password") String password);

    List<Hr> getHrsByKeywords(@Param("keywords") String keywords);

    int updateHr(Hr hr);

    int deleteRoleByHrId(Long hrId);

    int addRolesForHr(@Param("hrId") Long hrId, @Param("rids") Long[] rids);

    Hr getHrById(Long hrId);

    int deleteHr(Long hrId);

    List<Hr> getAllHr(@Param("currentId") Long currentId);
}
