package com.cs.qsx.controller;

import com.cs.qsx.domain.Msg;
import com.cs.qsx.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 包名 com.cs.qsx.controller
 * 类名 MsgController
 * 类描述 留言控制器
 *
 * @author Lucifer
 * @version 1.0
 * 创建日期 2017/11/16
 * 时间 15:41
 */
@Controller
public class MsgController {

    private final MsgService msgService;

    @Autowired
    public MsgController(MsgService msgService) {
        this.msgService = msgService;
    }

    @RequestMapping("/msg")
    public String showMsg() {
        return "leaveMessage";
    }

    @ResponseBody
    @RequestMapping("/api/msg")
    public List<Msg> msgapi() {
        List<Msg> msgs = msgService.findAll();
        return msgs;
    }

    @RequestMapping("/addMsg")
    public String addMsg(Msg msg) {
        msgService.addMsg(msg);
        return "leaveMessage";
    }

}
