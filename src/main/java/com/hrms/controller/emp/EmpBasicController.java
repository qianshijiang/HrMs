package com.hrms.controller.emp;

import com.hrms.common.HrUtils;
import com.hrms.common.util.RequestIPUtil;
import com.hrms.entity.Position;
import com.hrms.common.EmailRunnable;
import com.hrms.response.ResultModel;
import com.hrms.service.JobLevelService;
import com.hrms.service.OplogService;
import com.hrms.service.PositionService;
import com.hrms.entity.Employee;
import com.hrms.entity.RespBean;
import com.hrms.common.poi.PoiUtils;
import com.hrms.service.DepartmentService;
import com.hrms.service.EmpService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * Created by sang on 2018/1/12.
 */
@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {
    @Autowired
    private EmpService empService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private JobLevelService jobLevelService;
    @Autowired
    private ExecutorService executorService;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private OplogService oplogService;

    @RequestMapping(value = "/basicdata", method = RequestMethod.GET)
    public Map<String, Object> getAllNations(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try{
            map.put("nations", empService.getAllNations());
            map.put("politics", empService.getAllPolitics());
            map.put("deps", departmentService.getDepByPid(-1L));
            map.put("positions", positionService.getAllPos());
            map.put("joblevels", jobLevelService.getAllJobLevels());
            map.put("workID", String.format("%08d", empService.getMaxWorkID() + 1));
            String ip = RequestIPUtil.getIpAddr(request);
            this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"获取员工列表",ip);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("/maxWorkID")
    public String maxWorkID(HttpServletRequest request) {
        String result = "";
        try {
            String ip = RequestIPUtil.getIpAddr(request);
            result = String.format("%08d", empService.getMaxWorkID() + 1);
            this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"获取最大工号",ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public RespBean addEmp(Employee employee,HttpServletRequest request) {
        try{
            if (empService.addEmp(employee) == 1) {
                List<Position> allPos = positionService.getAllPos();
                for (Position allPo : allPos) {
                    if (allPo.getId() == employee.getPosId()) {
                        employee.setPosName(allPo.getName());
                    }
                }
                executorService.execute(new EmailRunnable(employee,
                    javaMailSender, templateEngine));
                String ip = RequestIPUtil.getIpAddr(request);
                this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"新增员工",ip);
                return RespBean.ok("添加成功!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespBean.error("添加失败!");
    }

    @RequestMapping(value = "/emp", method = RequestMethod.PUT)
    public RespBean updateEmp(Employee employee,HttpServletRequest request) {
        try{
            if (empService.updateEmp(employee) == 1) {
                String ip = RequestIPUtil.getIpAddr(request);
                this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"更新员工数据",ip);
                return RespBean.ok("更新成功!");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return RespBean.error("更新失败!");
    }

    @RequestMapping(value = "/emp/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteEmpById(@PathVariable String ids,HttpServletRequest request) {
        try{
            if (empService.deleteEmpById(ids)) {
                String ip = RequestIPUtil.getIpAddr(request);
                this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"删除员工",ip);
                return RespBean.ok("删除成功!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespBean.error("删除失败!");
    }

    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public Map<String, Object> getEmployeeByPage(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "") String keywords,
            Long politicId, Long nationId, Long posId,
            Long jobLevelId, String engageForm,
            Long departmentId, String beginDateScope,HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Employee> employeeByPage = empService.getEmployeeByPage(page, size,
                keywords,politicId, nationId, posId, jobLevelId, engageForm,
                departmentId, beginDateScope);
            Long count = empService.getCountByKeywords(keywords, politicId, nationId,
                posId,jobLevelId, engageForm, departmentId, beginDateScope);
            map.put("emps", employeeByPage);
            map.put("count", count);
            String ip = RequestIPUtil.getIpAddr(request);
            this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"获取员工列表分页查询",ip);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/exportEmp", method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportEmp(HttpServletRequest request) {
        try{
            ResponseEntity<byte[]> res = PoiUtils.exportEmp2Excel(empService.getAllEmployees());
            String ip = RequestIPUtil.getIpAddr(request);
            this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"导出excel",ip);
            return res;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/importEmp", method = RequestMethod.POST)
    public RespBean importEmp(MultipartFile file,HttpServletRequest request) {
        try{
            List<Employee> emps = PoiUtils.importEmp2List(file,
                empService.getAllNations(), empService.getAllPolitics(),
                departmentService.getAllDeps(), positionService.getAllPos(),
                jobLevelService.getAllJobLevels());
            if (empService.addEmps(emps) == emps.size()) {
                String ip = RequestIPUtil.getIpAddr(request);
                this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"导入excel",ip);
                return RespBean.ok("导入成功!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespBean.error("导入失败!");
    }

    @RequestMapping(value = "/getAllEmps",method = {RequestMethod.POST})
    public ResultModel getAppEmps(HttpServletRequest request){
      try{
          this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"获取员工列表",RequestIPUtil.getIpAddr(request));
          return ResultModel.success("获取员工列表成功",this.empService.getAllEmps(new Employee()));
      }catch (Exception e){
          e.printStackTrace();
          return ResultModel.fail("服务器发生未知错误，请稍后重试",e.getCause().getMessage());
      }
    }
}
