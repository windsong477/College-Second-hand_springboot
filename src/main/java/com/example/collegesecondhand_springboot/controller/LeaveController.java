package com.example.collegesecondhand_springboot.controller;


import com.example.collegesecondhand_springboot.common.Constants;
import com.example.collegesecondhand_springboot.common.Result;
import com.example.collegesecondhand_springboot.entity.Goods;
import com.example.collegesecondhand_springboot.entity.Leave;
import com.example.collegesecondhand_springboot.exception.ServiceException;
import com.example.collegesecondhand_springboot.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    LeaveService leaveService;

    //增加
    @PostMapping("/insertLeave")
    public Result addLeave(@RequestParam("Sid") String Sid, @RequestParam("Gid") String Gid,@RequestParam("Lmessage") String Lmessage) {
        if(leaveService.insertLeave(Sid,Gid,Lmessage))
        {
            return Result.success("留言成功");
        }
        else
        {
            throw new ServiceException(Constants.CODE_600, "后台异常");//自定义异常
        }

    }

    //删除
    @DeleteMapping("/deleteLeave/{Lid}&{Sid}")
    public Integer deleteLeave(@PathVariable("Lid") Long Lid,@PathVariable("Sid") Long Sid){
        return leaveService.deleteLeave(Lid,Sid);
    }

    //商品留言分页查询
    // 接口路径：/user/page?pageNum=1&pageSize=10
    // @RequestParam接受
    // limit第一个参数 = (pageNum - 1) * pageSize
    // pageSize
    @GetMapping("/getLeavePage")
    public Map<String, Object> getLeavePage(@RequestParam Integer pageNum,
                                            @RequestParam Integer pageSize,
                                            @RequestParam String Gid) {
        pageNum = (pageNum - 1) * pageSize;
        List<Leave> data = leaveService.getLeavePage(pageNum, pageSize, Gid);
        Integer total = leaveService.getGidTotal(Gid);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }

    //商品留言分页查询(使用Sid查询)
    // 接口路径：/user/page?pageNum=1&pageSize=10
    // @RequestParam接受
    // limit第一个参数 = (pageNum - 1) * pageSize
    // pageSize
    @GetMapping("/getLeavePageSid")
    public Map<String, Object> getLeavePageSid(@RequestParam Integer pageNum,
                                            @RequestParam Integer pageSize,
                                            @RequestParam String Sid) {
        pageNum = (pageNum - 1) * pageSize;
        List<Leave> data = leaveService.getLeavePageSid(pageNum, pageSize, Sid);
        Integer total = leaveService.getSidTotal(Sid);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }

}
