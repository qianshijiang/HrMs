package com.hrms.controller.salary;

import com.hrms.common.HrUtils;
import com.hrms.common.util.RequestIPUtil;
import com.hrms.entity.RespBean;
import com.hrms.entity.Salary;
import com.hrms.entity.Employee;
import com.hrms.service.EmpService;
import com.hrms.service.OplogService;
import com.hrms.service.SalaryService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sang on 2018/1/25.
 */
@RestController
@RequestMapping("/salary/sobcfg")
public class SalaryEmpController {
    @Autowired
    private SalaryService salaryService;
    @Autowired
    private EmpService empService;
    @Autowired
    private OplogService oplogService;

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public RespBean updateEmpSalary(Integer sid, Long eid,HttpServletRequest request) {
        try{
            if (salaryService.updateEmpSalary(sid, eid) == 1) {
                this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"修改员工套账信息", RequestIPUtil.getIpAddr(request));
                return RespBean.ok("修改成功!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespBean.error("修改失败!");
    }

    @RequestMapping(value = "/salaries", method = RequestMethod.GET)
    public List<Salary> salaries(HttpServletRequest request) {
        try{
            List<Salary> salList = salaryService.getAllSalary();
            this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"获取套账列表",RequestIPUtil.getIpAddr(request));
            return salList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public Map<String, Object> getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size,HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try{
            List<Employee> employeeByPage = empService.getEmployeeByPageShort(page, size);
            Long count = empService.getCountByKeywords("", null, null, null, null, null, null, null);
            map.put("emps", employeeByPage);
            map.put("count", count);
            this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"分页获取员工列表",RequestIPUtil.getIpAddr(request));
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
}
