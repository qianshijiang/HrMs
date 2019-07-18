package com.hrms.controller.salary;

import com.hrms.common.HrUtils;
import com.hrms.common.util.RequestIPUtil;
import com.hrms.entity.Department;
import com.hrms.service.DepartmentService;
import com.hrms.service.OplogService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by sang on 2018/1/26.
 */
@RestController
@RequestMapping("/salary/table")
public class SalaryTableController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private OplogService oplogService;

    @RequestMapping("/deps")
    public List<Department> departments(HttpServletRequest request) {
        try{
            List<Department> deptList = departmentService.getAllDeps();
            this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"获取部门列表", RequestIPUtil.getIpAddr(request));
            return deptList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
