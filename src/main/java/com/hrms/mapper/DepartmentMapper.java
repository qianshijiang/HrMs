package com.hrms.mapper;

import com.hrms.common.base.BaseMapper;
import com.hrms.entity.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by sang on 2018/1/7.
 */
public interface DepartmentMapper extends BaseMapper<Department,String>{
    void addDep(@Param("dep") Department department);

    void deleteDep(@Param("dep") Department department);

    List<Department> getDepByPid(Long pid);

    List<Department> getAllDeps();
}
