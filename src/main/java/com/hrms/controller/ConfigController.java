package com.hrms.controller;

import com.hrms.common.util.RequestIPUtil;
import com.hrms.entity.Hr;
import com.hrms.entity.Menu;
import com.hrms.common.HrUtils;
import com.hrms.service.MenuService;
import com.hrms.service.OplogService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 这是一个只要登录就能访问的Controller
 * 主要用来获取一些配置信息
 */
@RestController
@RequestMapping("/config")
public class ConfigController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private OplogService oplogService;
    @RequestMapping("/sysmenu")
    public List<Menu> sysmenu(HttpServletRequest request) {
        try{
            List<Menu> menuList = menuService.getMenusByHrId();
            this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"获取菜单列表", RequestIPUtil.getIpAddr(request));
            return menuList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/hr")
    public Hr currentUser(HttpServletRequest request) {
        try{
            this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"获取登录Hr相关信息",RequestIPUtil.getIpAddr(request));
            return HrUtils.getCurrentHr();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
