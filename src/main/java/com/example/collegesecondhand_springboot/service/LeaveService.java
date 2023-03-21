package com.example.collegesecondhand_springboot.service;

import com.example.collegesecondhand_springboot.controller.LeaveController;
import com.example.collegesecondhand_springboot.entity.Goods;
import com.example.collegesecondhand_springboot.entity.Leave;
import com.example.collegesecondhand_springboot.mapper.LeaveMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    @Autowired
    LeaveMapper leaveMapper;

    //插入
    public Boolean insertLeave(@Param("Sid") String Sid, @Param("Gid") String Gid,@Param("Lmessage") String Lmessage){
        try {
            return leaveMapper.insertLeave(Sid,Gid,Lmessage);
        }catch (Exception e)
        {
            return false;
        }
    }

    //删除
    public Integer deleteLeave(long Lid,long Sid){
        return leaveMapper.deleteLeave(Lid,Sid);
    }

    //根据Gid查询数据
    public List<Leave> getLeavePage(Integer pageNum, Integer pageSize, String Gid){
        return leaveMapper.getLeavePage(pageNum, pageSize, Gid);
    }

    //查询Gid的所有数据量
    public int getGidTotal(String Gid){
        return leaveMapper.getGidTotal(Gid);
    }


    //根据Sid查询数据
    public List<Leave> getLeavePageSid(Integer pageNum, Integer pageSize, String Sid){
        return leaveMapper.getLeavePageSid(pageNum, pageSize, Sid);
    }

    //查询Gid的所有数据量
    public int getSidTotal(String Sid){
        return leaveMapper.getSidTotal(Sid);
    }
}
