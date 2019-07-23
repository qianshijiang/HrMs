package com.hrms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hrms.response.ResultModel;
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
@RequestMapping(value = "/oplog/info")
public class OplogController {

  @Autowired
  private OplogService oplogService;

  @RequestMapping(value = "/getOplogList",method = {RequestMethod.POST})
  public ResultModel getOplogList(@RequestParam(defaultValue = "") String addDate,
                                  @RequestParam(defaultValue = "") String hrName,
                                  @RequestParam(defaultValue = "") String IP,
                                  @RequestParam(defaultValue = "10") Integer currentPage,
                                  @RequestParam(defaultValue = "1") Integer pageSize,HttpServletRequest request){
     try{
       ResultModel model = this.oplogService.findListByPage(addDate,hrName,IP,currentPage,pageSize);
       return ResultModel.successfull("获取操作日志列表成功",model);
     }catch (Exception e){
       e.printStackTrace();
       return ResultModel.fail("服务器发生未知错误，请稍后重试",e.getCause().getMessage());
     }
  }

}
