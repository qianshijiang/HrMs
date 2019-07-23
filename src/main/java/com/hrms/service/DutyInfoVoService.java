package com.hrms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrms.common.HrUtils;
import com.hrms.common.util.DateUtil;
import com.hrms.entity.Duty;
import com.hrms.entity.Dutyinfo;
import com.hrms.entity.vo.DutyInfoVo;
import com.hrms.mapper.DutyInfoVoMapper;
import com.hrms.mapper.DutyMapper;
import com.hrms.mapper.DutyinfoMapper;
import com.hrms.response.ResultModel;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by QSJ on 2019/7/18.
 */
@Service
public class DutyInfoVoService {

  @Autowired
  private DutyInfoVoMapper dutyInfoVoMapper;

  @Autowired
  private DutyinfoMapper dutyinfoMapper;

  @Autowired
  private DutyMapper dutyMapper;

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

  /**
   * 添加值班信息
   * @param div
   * @return
   */
  public int addDutyInfo(DutyInfoVo div) throws Exception{
    int s = 0;
    Dutyinfo dyf = new Dutyinfo();
    dyf.setDutyTime(DateUtil.getInDate(div.getDutyTime(),"yyyy-MM-dd HH:mm:ss"));
    dyf.setCreateTime(DateUtil.getInDate("yyyy-MM-dd HH:mm:ss"));
    dyf.setOperatorId(HrUtils.getCurrentHr().getId());
    dyf.setRemarks(div.getRemarks());
    dyf.setUpdateTime(DateUtil.getInDate("yyyy-MM-dd HH:mm:ss"));
    s = this.dutyinfoMapper.insertSelective(dyf);
    if(s>0 && dyf.getDutyInfoId()!=null){
       for(Long empid:div.getEmpIdList()){
         Duty duty = new Duty();
         duty.setDutyinfoid(dyf.getDutyInfoId());
         duty.setEmployeeid(empid);
         duty.setCreatedate(DateUtil.getInDate("yyyy-MM-dd HH:mm:ss"));
         duty.setUpdatedate(DateUtil.getInDate("yyyy-MM-dd HH:mm:ss"));
         s += this.dutyMapper.insertSelective(duty);
       }
    }
    return s;
  }

}
