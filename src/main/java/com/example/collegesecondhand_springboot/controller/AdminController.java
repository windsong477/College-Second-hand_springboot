package com.example.collegesecondhand_springboot.controller;

import com.example.collegesecondhand_springboot.common.Constants;
import com.example.collegesecondhand_springboot.common.Result;
import com.example.collegesecondhand_springboot.entity.Admin;
import com.example.collegesecondhand_springboot.exception.ServiceException;

import com.example.collegesecondhand_springboot.service.AdminService;
import com.example.collegesecondhand_springboot.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    //登录
    @GetMapping("/login")
    public Result login(@RequestParam String Ausername, @RequestParam String Apassword){
        if(adminService.login(Ausername,Apassword)!=null)
        {
            // 设置token
            String AuserId=adminService.findId(Ausername);//根据用户名去查找ID
            String token = TokenUtils.genToken(AuserId, Apassword);
            Admin admin = adminService.login(Ausername,Apassword);
            admin.setToken(token);

            return Result.success(admin);
        }
        else
        {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");//自定义异常
        }

    }

    //注册
    @PostMapping("/registerAdmin")
    public Result register(@RequestParam String Ausername, @RequestParam String Apassword){
        if(adminService.registerAdmin(Ausername,Apassword))
        {
            return Result.success("注册成功");
        }
        else
        {
            throw new ServiceException(Constants.CODE_600, "该用户名已被注册");//自定义异常
        }
    }

    //修改个人密码
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestParam("Aid") String Aid,@RequestParam String Apassword, @RequestParam String Anewpassword){
        //为了将前端传来的string类型转化为数据库对应的int类型，做出如下操作
        Integer Iid = Integer.parseInt(Aid);

        if(adminService.updatePassword(Iid,Apassword, Anewpassword))
        {
            return Result.success("修改密码成功");
        }
        else
        {
            throw new ServiceException(Constants.CODE_600, "修改失败：密码错误");//自定义异常
        }
    }

    //查询
    @GetMapping("/getAdmin")
    public List<Admin> getAdmin() {
        return adminService.findAll();
    }

    //根据id查询数据
    @GetMapping("/getOneAdmin")
    public Admin findOne(@RequestParam("Aid") String Aid){
        Integer id = Integer.parseInt(Aid);
        return adminService.findOne(id);
    }

    //增加
    @PostMapping("/insertAdmin")
    public Result addAdmin(@RequestBody Admin admin) {
        if(adminService.insertAdmin(admin))
        {
            return Result.success("添加成功");
        }
        else
        {
            throw new ServiceException(Constants.CODE_600, "用户名重复");//自定义异常
        }

    }
    //修改
    @PutMapping("/updateAdmin")
    public Result updateAdmin(@RequestBody Admin admin){
        if(adminService.updateAdmin(admin))
        {
            return Result.success("修改成功");
        }
        else
        {
            throw new ServiceException(Constants.CODE_600, "用户名重复");//自定义异常
        }
    }
    //删除
    @DeleteMapping("/deleteAdmin/{Aid}")
    public Integer deleteAdmin(@PathVariable("Aid") Long Aid){
        return adminService.deleteAdmin(Aid);
    }
    //批量删除
    @PostMapping("/deleteAdmin/batch")
    public Integer deleteAdminBatch(@RequestBody List<Integer> Aids){
        return adminService.deleteAdminBatch(Aids);
    }

    //分页查询
    // 接口路径：/user/page?pageNum=1&pageSize=10
    // @RequestParam接受
    // limit第一个参数 = (pageNum - 1) * pageSize
    // pageSize
    @GetMapping("/getAdminPage")
    public Map<String, Object> getAdminPage(@RequestParam Integer pageNum,
                                           @RequestParam Integer pageSize,
                                           @RequestParam String Ausername) {
        pageNum = (pageNum - 1) * pageSize;
        List<Admin> data = adminService.getAdminPage(pageNum, pageSize, Ausername);
        Integer total = adminService.getTotal(Ausername);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }


    //查询总管理员数
    @GetMapping("/getAdminAllTotal")
    public int getAllTotal(){
        return adminService.getAllTotal();
    }
}
