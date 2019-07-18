package com.hrms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hrms.common.HrUtils;
import com.hrms.common.util.RequestIPUtil;
import com.hrms.response.ResultModel;
import com.hrms.service.DutyInfoVoService;
import com.hrms.service.OplogService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
  public ResultModel getListByPage(@RequestBody String param,HttpServletRequest request){
    try{
      JSONObject object = JSON.parseObject(param,JSONObject.class);
      String dutyTime = object.get("dutyTime")==null?"":object.getString("dutyTime");
      String empName = object.get("empName")==null?"":object.getString("empName");
      String workId = object.get("workId")==null?"":object.getString("workId");
      int currentPage = object.get("currentPage")==null?1:object.getInteger("currentPage");
      int pageSize = object.get("pageSize")==null?10:object.getInteger("pageSize");

      ResultModel model = this.dutyInfoVoService.findListByPage(dutyTime,empName,workId,currentPage,pageSize);
      this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"获取值班列表", RequestIPUtil.getIpAddr(request));

      return ResultModel.successfull("获取值班列表成功",model);
    }catch (Exception e){
      e.printStackTrace();
      return ResultModel.fail("服务器发生未知错误，请稍后重试",e.getCause().getMessage());
    }
  }





}
