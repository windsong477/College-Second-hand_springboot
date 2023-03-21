package com.example.collegesecondhand_springboot.service;

import com.example.collegesecondhand_springboot.entity.Admin;
import com.example.collegesecondhand_springboot.mapper.AdminMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    //登录
    public Admin login(String Ausername,String Apassword){
        return adminMapper.login(Ausername,Apassword);
    }

    //注册
    public Boolean registerAdmin(@Param("Ausername") String Ausername,@Param("Apassword") String Apassword){
        try {
            return adminMapper.registerAdmin(Ausername,Apassword);
        }catch (Exception e)
        {
            return false;
        }

    }

    //查询
    public List<Admin> findAll(){
        return adminMapper.findAll();
    }

    //根据用户名查找ID
    public String findId(@Param("Ausername") String Ausername){
        return adminMapper.findId(Ausername);
    }

    //根据id查找所有数据
    public Admin findOne(@Param("Aid") Integer Aid){
        return adminMapper.findOne(Aid);
    }

    //插入
    public Boolean insertAdmin(Admin admin){
        try {
            return adminMapper.insertAdmin(admin);
        }catch (Exception e)
        {
            return false;
        }
    }

    //修改
    public Boolean updateAdmin(Admin admin){
        try {
            return adminMapper.updateAdmin(admin);
        }catch (Exception e)
        {
            return false;

        }

    }

    //修改个人密码
    public Boolean updatePassword(Integer Aid,String Apassword,String Anewpassword){
        try {
            return adminMapper.updatePassword(Aid,Apassword, Anewpassword);
        }catch (Exception e)
        {
            return false;

        }

    }

    //删除
    public Integer deleteAdmin(long Aid){
        return adminMapper.deleteAdmin(Aid);
    }

    //批量删除
    public Integer deleteAdminBatch(List<Integer> Aids){
        return adminMapper.deleteAdminBatch(Aids);
    }

    //分页查询
    public List<Admin> getAdminPage(Integer pageNum,Integer pageSize,String Ausername){
        return adminMapper.getAdminPage(pageNum, pageSize, Ausername);
    }

    //查询所有数据量
    public int getTotal(String Ausername){
        return adminMapper.getTotal(Ausername);
    }

    //查询管理员总数
    public int getAllTotal(){
        return adminMapper.getAllTotal();
    }
}
