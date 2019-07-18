package com.hrms.mapper;

import com.hrms.common.base.BaseMapper;
import com.hrms.entity.Hr;
import com.hrms.entity.MsgContent;
import com.hrms.entity.SysMsg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by sang on 2018/2/2.
 */
public interface SysMsgMapper extends BaseMapper<SysMsg,String>{

    int sendMsg(MsgContent msg);

    int addMsg2AllHr(@Param("hrs") List<Hr> hrs, @Param("mid") Long mid);

    List<SysMsg> getSysMsg(@Param("start") int start, @Param("size") Integer size,@Param("hrid") Long hrid);

    int markRead(@Param("flag") Long flag, @Param("hrid") Long hrid);
}
