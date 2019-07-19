package com.hrms.controller;

import com.hrms.common.HrUtils;
import com.hrms.common.util.RequestIPUtil;
import com.hrms.entity.Hr;
import com.hrms.entity.MsgContent;
import com.hrms.entity.RespBean;
import com.hrms.entity.SysMsg;
import com.hrms.service.HrService;
import com.hrms.service.OplogService;
import com.hrms.service.SysMsgService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 处理通知消息的Controller
 * 登录即可访问
 */
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private HrService hrService;
    @Autowired
    private SysMsgService sysMsgService;
    @Autowired
    private OplogService oplogService;

    @RequestMapping(value = "/hrs", method = RequestMethod.GET)
    public List<Hr> getAllHr(HttpServletRequest request) {
        try{
            List<Hr> hrList = hrService.getAllHrExceptAdmin();
            this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"获取Hr列表", RequestIPUtil.getIpAddr(request));
            return hrList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/nf", method = RequestMethod.POST)
    public RespBean sendNf(MsgContent msg,HttpServletRequest request) {
        try{
            if (sysMsgService.sendMsg(msg)) {
                this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"发送消息",RequestIPUtil.getIpAddr(request));
                return RespBean.ok("发送成功!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return RespBean.error("发送失败!");
    }

    @RequestMapping("/sysmsgs")
    public List<SysMsg> getSysMsg(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size,HttpServletRequest request) {
        try{
            List<SysMsg> sysMsgList = sysMsgService.getSysMsgByPage(page, size);
            this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"分页获取系统消息",RequestIPUtil.getIpAddr(request));
            return sysMsgList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping(value = "/markread", method = RequestMethod.PUT)
    public RespBean markRead(Long flag,HttpServletRequest request) {
        try{
            if (sysMsgService.markRead(flag)) {
                this.oplogService.insertSelective(HrUtils.getCurrentHr().getId(),"更新消息为已读状态",RequestIPUtil.getIpAddr(request));
                if (flag == -1) {
                    return RespBean.ok("multiple");
                } else {
                    return RespBean.ok("single");
                }
            } else {
                if (flag == -1) {
                    return RespBean.error("multiple");
                } else {
                    return RespBean.error("single");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
