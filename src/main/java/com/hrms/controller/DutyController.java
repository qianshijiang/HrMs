package com.hrms.controller;

import com.hrms.common.HrUtils;
import com.hrms.common.util.RequestIPUtil;
import com.hrms.entity.vo.DutyInfoVo;
import com.hrms.response.ResultModel;
import com.hrms.service.DutyInfoVoService;
import com.hrms.service.OplogService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by QSJ on 2019/7/18.
 */
@RestController
@RequestMapping(value = "/duty/info")
public class DutyController {

  @Autowired
  private DutyInfoVoService dutyInfoVoService;

  @Autowired
  private OplogService oplogService;

  @RequestMapping(value = "/getListByPage",method = {RequestMethod.POST})
  public ResultModel getListByPage(@RequestParam(defaultValue = "") String dutyTime,
                                   @RequestParam(defaultValue = "") String empName,
                                   @RequestParam(defaultValue = "") String workId,
                                   @RequestParam(defaultValue = "1") Integer currentPage,
                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                   HttpServletRequest request){
    try{
      ResultModel model = this.dutyInfoVoService.findListByPage(dutyTime,empName,workId,currentPage,pageSize);
      this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"获取值班列表", RequestIPUtil.getIpAddr(request));

      return ResultModel.successfull("获取值班列表成功",model);
    }catch (Exception e){
      e.printStackTrace();
      return ResultModel.fail("服务器发生未知错误，请稍后重试",e.getCause().getMessage());
    }
  }

  @RequestMapping(value = "/addDutyInfo",method = {RequestMethod.POST})
  public ResultModel addDutyInfo(DutyInfoVo div,HttpServletRequest request){
    try{
        int i = this.dutyInfoVoService.addDutyInfo(div);
        if(i>0){
          ResultModel.success("添加成功",null);
        }else{
          ResultModel.fail("添加失败","");
        }
    }catch (Exception e){
      e.printStackTrace();
      return ResultModel.fail("服务器发生未知错误，请稍后重试",e.getCause().getMessage());
    }
    return null;
  }





}
