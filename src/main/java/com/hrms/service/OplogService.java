package com.hrms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrms.common.util.DateUtil;
import com.hrms.common.util.RequestIPUtil;
import com.hrms.entity.Oplog;
import com.hrms.mapper.OplogMapper;
import com.hrms.response.ResultModel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by QSJ on 2019/7/17.
 */
@Service
public class OplogService {

  @Autowired
  private OplogMapper oplogMapper;

  /**
   * 根据Id删除日志
   * @param id 日志id
   * @return
   */
  public int deleteByPrimaryKey(Integer id){
    return this.oplogMapper.deleteByPrimaryKey(id);
  }

  /**
   * 日志新增
   * @param hrid 操作者id
   * @param descript 操作描述
   * @param ip 操作ip
   * @return
   */
  public int insertSelective(Long hrid,String descript,String ip) throws Exception{
    Oplog oplog = new Oplog();
    oplog.setAddDate(DateUtil.getInDate("yyyy-MM-dd HH:mm:ss"));
    oplog.setIp(ip);
    oplog.setHrid(hrid);
    oplog.setOperate(descript);
    return this.oplogMapper.insertSelective(oplog);
  }

  /**
   * 根绝id查询
   * @param id 日志id
   * @return
   */
  public Oplog selectByPrimaryKey(Integer id){
    return this.oplogMapper.selectByPrimaryKey(id);
  }

  /**
   * 更新日志 非空对象更新
   * @param oplog 日志对象
   * @return
   */
  public int updateByPrimaryKeySelective(Oplog oplog){
     return this.oplogMapper.updateByPrimaryKeySelective(oplog);
  }

  /**
   * 分页查询日志
   * @param addDate 操作时间
   * @param hrName 操作人
   * @param IP IP地址
   * @param currentPage 页码
   * @param pageSize 页数
   * @return
   */
  public ResultModel findListByPage(String addDate,String hrName,String IP,int currentPage,int pageSize){
     //设置分页信息，分别是当前页数和每页显示的总记录数
    //PageHelper.startPage(1,10)只对后面紧跟的第一个查询,查出的数据进行分页(https://blog.csdn.net/csdn___lyy/article/details/77160488)
    /*int count = this.lawCaseMapper.findListTimeoutCount(areaCode);*/
    PageHelper.startPage(currentPage,pageSize);
    //获取操作日志
    List<Oplog> oplogList = this.oplogMapper.getOplogBydate(addDate,hrName,IP);
    PageInfo<Oplog> info = new PageInfo<>(oplogList);
    ResultModel pageData = new ResultModel(currentPage, pageSize, Integer.valueOf(String.valueOf(info.getTotal())));
    pageData.setData(oplogList);
    return pageData;
  }

}
