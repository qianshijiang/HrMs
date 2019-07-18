package com.hrms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrms.entity.vo.DutyInfoVo;
import com.hrms.mapper.DutyInfoVoMapper;
import com.hrms.response.ResultModel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by QSJ on 2019/7/18.
 */
@Service
public class DutyInfoVoService {

  @Autowired
  private DutyInfoVoMapper dutyInfoVoMapper;

  /**
   * 获取值班列表
   * @param dutyTime 值班时间
   * @param empName 员工姓名
   * @param workId 员工工号
   * @param currentPage 页码
   * @param pageSize 每页显示条数
   * @return
   */
  public ResultModel findListByPage(String dutyTime,String empName,String workId,int currentPage,int pageSize){
    PageHelper.startPage(currentPage,pageSize);
    List<DutyInfoVo> dtVoList = this.dutyInfoVoMapper.findListByPage(dutyTime,empName,workId);
    PageInfo<DutyInfoVo> info = new PageInfo<>(dtVoList);
    ResultModel pageData = new ResultModel(currentPage, pageSize, Integer.valueOf(String.valueOf(info.getTotal())));
    pageData.setData(dtVoList);
    return pageData;
  }
}
