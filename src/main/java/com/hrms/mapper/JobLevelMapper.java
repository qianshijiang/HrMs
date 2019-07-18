package com.hrms.mapper;

import com.hrms.common.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.hrms.entity.JobLevel;

import java.util.List;

/**
 * Created by sang on 2018/1/11.
 */
public interface JobLevelMapper extends BaseMapper<JobLevel,String>{
    JobLevel getJobLevelByName(String name);

    int addJobLevel(@Param("jobLevel") JobLevel jobLevel);

    List<JobLevel> getAllJobLevels();

    int deleteJobLevelById(@Param("ids") String[] ids);

    int updateJobLevel(@Param("jobLevel") JobLevel jobLevel);
}
