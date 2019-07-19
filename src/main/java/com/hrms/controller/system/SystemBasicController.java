package com.hrms.controller.system;

import com.hrms.common.HrUtils;
import com.hrms.common.util.RequestIPUtil;
import com.hrms.entity.Department;
import com.hrms.entity.JobLevel;
import com.hrms.entity.Menu;
import com.hrms.entity.Position;
import com.hrms.entity.RespBean;
import com.hrms.entity.Role;
import com.hrms.service.DepartmentService;
import com.hrms.service.JobLevelService;
import com.hrms.service.MenuRoleService;
import com.hrms.service.MenuService;
import com.hrms.service.OplogService;
import com.hrms.service.PositionService;
import com.hrms.service.RoleService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sang on 2017/12/29.
 */
@RestController
@RequestMapping("/system/basic")
public class SystemBasicController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuRoleService menuRoleService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private JobLevelService jobLevelService;
    @Autowired
    private OplogService oplogService;

    @RequestMapping(value = "/role/{rid}", method = RequestMethod.DELETE)
    public RespBean deleteRole(@PathVariable Integer rid,HttpServletRequest request) {
        try{
            if (roleService.deleteRoleById(rid) == 1) {
                this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"删除角色", RequestIPUtil.getIpAddr(request));
                return RespBean.ok("删除成功!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespBean.error("删除失败!");
    }

    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public RespBean addNewRole(String role, String roleZh,HttpServletRequest request) {
        try{
            if (roleService.addNewRole(role, roleZh) == 1) {
                this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"添加角色",RequestIPUtil.getIpAddr(request));
                return RespBean.ok("添加成功!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespBean.error("添加失败!");
    }

    @RequestMapping(value = "/menuTree/{rid}", method = RequestMethod.GET)
    public Map<String, Object> menuTree(@PathVariable Long rid,HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try{
            List<Menu> menus = menuService.menuTree();
            map.put("menus", menus);
            List<Long> selMids = menuService.getMenusByRid(rid);
            map.put("mids", selMids);
            this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"获取菜单目录",RequestIPUtil.getIpAddr(request));
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/updateMenuRole", method = RequestMethod.PUT)
    public RespBean updateMenuRole(Long rid, Long[] mids,HttpServletRequest request) {
        try{
            if (menuRoleService.updateMenuRole(rid, mids) == mids.length) {
                this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"更新菜单角色",RequestIPUtil.getIpAddr(request));
                return RespBean.ok("更新成功!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespBean.error("更新失败!");
    }

    @RequestMapping("/roles")
    public List<Role> allRoles(HttpServletRequest request) {
        try{
            List<Role> roleList = roleService.roles();
            this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"获取角色列表",RequestIPUtil.getIpAddr(request));
            return roleList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/dep", method = RequestMethod.POST)
    public Map<String, Object> addDep(Department department,HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try{
            if (departmentService.addDep(department) == 1) {
                map.put("status", "success");
                map.put("msg", department);
                this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"添加部门",RequestIPUtil.getIpAddr(request));
                return map;
            }
            map.put("status", "error");
            map.put("msg", "添加失败!");
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/dep/{did}", method = RequestMethod.DELETE)
    public RespBean deleteDep(@PathVariable Long did,HttpServletRequest request) {
        try{
            if (departmentService.deleteDep(did) == 1) {
                this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"删除部门",RequestIPUtil.getIpAddr(request));
                return RespBean.ok("删除成功!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespBean.error("删除失败!");
    }

    @RequestMapping(value = "/dep/{pid}", method = RequestMethod.GET)
    public List<Department> getDepByPid(@PathVariable Long pid,HttpServletRequest request) {
        try{
            List<Department> deptList = departmentService.getDepByPid(pid);
            this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"通过父级主键获取部门列表",RequestIPUtil.getIpAddr(request));
            return deptList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/deps", method = RequestMethod.GET)
    public List<Department> getAllDeps(HttpServletRequest request) {
        try{
            List<Department> deptList = departmentService.getAllDeps();
            this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"获取部门列表",RequestIPUtil.getIpAddr(request));
            return deptList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/position", method = RequestMethod.POST)
    public RespBean addPos(Position pos,HttpServletRequest request) {
        try{
            int result = positionService.addPos(pos);
            if (result == 1) {
                this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"添加职位",RequestIPUtil.getIpAddr(request));
                return RespBean.ok("添加成功!");
            } else if (result == -1) {
                return RespBean.error("职位名重复，添加失败!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespBean.error("添加失败!");
    }

    @RequestMapping(value = "/positions", method = RequestMethod.GET)
    public List<Position> getAllPos(HttpServletRequest request) {
        try{
            List<Position> positionList = positionService.getAllPos();
            this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"获取职位列表",RequestIPUtil.getIpAddr(request));
            return positionList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/position/{pids}")
    public RespBean deletePosById(@PathVariable String pids,HttpServletRequest request) {
        try{
            if (positionService.deletePosById(pids)) {
                this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"删除职位",RequestIPUtil.getIpAddr(request));
                return RespBean.ok("删除成功!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespBean.error("删除失败!");
    }

    @RequestMapping(value = "/position", method = RequestMethod.PUT)
    public RespBean updatePosById(Position position,HttpServletRequest request) {
        try{
            if (positionService.updatePosById(position) == 1) {
                this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"更新职位信息",RequestIPUtil.getIpAddr(request));
                return RespBean.ok("修改成功!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespBean.error("修改失败!");
    }

    @RequestMapping(value = "/joblevel", method = RequestMethod.POST)
    public RespBean addJobLevel(JobLevel jobLevel,HttpServletRequest request) {
        try{
            int result = jobLevelService.addJobLevel(jobLevel);
            if (result == 1) {
                this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"添加职称数据",RequestIPUtil.getIpAddr(request));
                return RespBean.ok("添加成功!");
            } else if (result == -1) {
                return RespBean.error("职称名重复，添加失败!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespBean.error("添加失败!");
    }

    @RequestMapping(value = "/joblevels", method = RequestMethod.GET)
    public List<JobLevel> getAllJobLevels(HttpServletRequest request) {
        try{
            List<JobLevel> jobLevelList = jobLevelService.getAllJobLevels();
            this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"获取职称列表",RequestIPUtil.getIpAddr(request));
            return jobLevelList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/joblevel/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteJobLevelById(@PathVariable String ids,HttpServletRequest request) {
        try{
            if (jobLevelService.deleteJobLevelById(ids)) {
                this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"根据职称主键删除职称",RequestIPUtil.getIpAddr(request));
                return RespBean.ok("删除成功!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespBean.error("删除失败!");
    }

    @RequestMapping(value = "/joblevel", method = RequestMethod.PUT)
    public RespBean updateJobLevel(JobLevel jobLevel,HttpServletRequest request) {
        try{
            if (jobLevelService.updateJobLevel(jobLevel) == 1) {
                this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"更新职称信息",RequestIPUtil.getIpAddr(request));
                return RespBean.ok("修改成功!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespBean.error("修改失败!");
    }
}
