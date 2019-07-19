package com.hrms.controller.system;

import com.hrms.common.HrUtils;
import com.hrms.common.util.RequestIPUtil;
import com.hrms.service.HrService;
import com.hrms.entity.Hr;
import com.hrms.entity.RespBean;
import com.hrms.service.OplogService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by sang on 2018/1/2.
 */
@RestController
@RequestMapping("/system/hr")
public class SystemHrController {
    @Autowired
    private HrService hrService;

    @Autowired
    private OplogService oplogService;

    @RequestMapping("/id/{hrId}")
    public Hr getHrById(@PathVariable Long hrId,HttpServletRequest request) {
        try{
            this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"根据Hr主键获取相关信息", RequestIPUtil.getIpAddr(request));
            return hrService.getHrById(hrId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/{hrId}", method = RequestMethod.DELETE)
    public RespBean deleteHr(@PathVariable Long hrId,HttpServletRequest request) {
        try{
            if (hrService.deleteHr(hrId) == 1) {
                this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"通过Hr主键删除Hr",RequestIPUtil.getIpAddr(request));
                return RespBean.ok("删除成功!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespBean.error("删除失败!");
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public RespBean updateHr(Hr hr,HttpServletRequest request) {
        try{
            if (hrService.updateHr(hr) == 1) {
                this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"更新Hr相关信息",RequestIPUtil.getIpAddr(request));
                return RespBean.ok("更新成功!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespBean.error("更新失败!");
    }

    @RequestMapping(value = "/roles", method = RequestMethod.PUT)
    public RespBean updateHrRoles(Long hrId, Long[] rids,HttpServletRequest request) {
        try{
            if (hrService.updateHrRoles(hrId, rids) == rids.length) {
                this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"更新Hr角色信息",RequestIPUtil.getIpAddr(request));
                return RespBean.ok("更新成功!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespBean.error("更新失败!");
    }

    @RequestMapping("/{keywords}")
    public List<Hr> getHrsByKeywords(@PathVariable(required = false) String keywords,HttpServletRequest request) {
        try{
            List<Hr> hrs = hrService.getHrsByKeywords(keywords);
            this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"通过关键字获取Hr信息",RequestIPUtil.getIpAddr(request));
            return hrs;
        }catch (Exception e){
            e.printStackTrace();
        }
       return null;
    }


    @RequestMapping(value = "/hr/reg", method = RequestMethod.POST)
    public RespBean hrReg(String username, String password,HttpServletRequest request) {
        try{
            int i = hrService.hrReg(username, password);
            if (i == 1) {
                this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"Hr注册",RequestIPUtil.getIpAddr(request));
                return RespBean.ok("注册成功!");
            } else if (i == -1) {
                return RespBean.error("用户名重复，注册失败!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespBean.error("注册失败!");
    }

}
