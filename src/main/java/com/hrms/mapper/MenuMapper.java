package com.hrms.mapper;

import com.hrms.common.base.BaseMapper;
import com.hrms.entity.Menu;

import java.util.List;

/**
 * Created by sang on 2017/12/28.
 */
public interface MenuMapper extends BaseMapper<Menu,String>{
    List<Menu> getAllMenu();

    List<Menu> getMenusByHrId(Long hrId);

    List<Menu> menuTree();

    List<Long> getMenusByRid(Long rid);
}
