package com.hrms.controller.salary;

import com.hrms.common.HrUtils;
import com.hrms.common.util.RequestIPUtil;
import com.hrms.entity.RespBean;
import com.hrms.entity.Salary;
import com.hrms.service.EmpService;
import com.hrms.service.OplogService;
import com.hrms.service.SalaryService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 工资账套配置
 */
@RestController
@RequestMapping("/salary/sob")
public class SalaryController {
    @Autowired
    private SalaryService salaryService;
    @Autowired
    private EmpService empService;
    @Autowired
    private OplogService oplogService;

    @RequestMapping(value = "/salary", method = RequestMethod.POST)
    public RespBean addSalaryCfg(Salary salary,HttpServletRequest request) {
        try{
            if (salaryService.addSalary(salary) == 1) {
                this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"添加工资账套", RequestIPUtil.getIpAddr(request));
                return RespBean.ok("添加成功!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespBean.error("添加失败!");
    }

    @RequestMapping(value = "/salary", method = RequestMethod.GET)
    public List<Salary> salaries(HttpServletRequest request) {
        try{
            List<Salary> salaList = salaryService.getAllSalary();
            this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"获取工资套账配置列表",RequestIPUtil.getIpAddr(request));
            return salaList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping(value = "/salary", method = RequestMethod.PUT)
    public RespBean updateSalary(Salary salary,HttpServletRequest request) {
        try{
            if (salaryService.updateSalary(salary) == 1) {
                this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"更新工资套账配置",RequestIPUtil.getIpAddr(request));
                return RespBean.ok("更新成功!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespBean.error("更新失败!");
    }

    @RequestMapping(value = "/salary/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteSalary(@PathVariable String ids,HttpServletRequest request) {
        try{
            if (salaryService.deleteSalary(ids) == 1) {
                this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"删除工资套账配置",RequestIPUtil.getIpAddr(request));
                return RespBean.ok("删除成功!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespBean.error("删除失败!");
    }

}
