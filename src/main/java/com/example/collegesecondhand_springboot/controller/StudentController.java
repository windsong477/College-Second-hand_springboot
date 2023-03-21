package com.example.collegesecondhand_springboot.controller;

import com.example.collegesecondhand_springboot.common.Constants;
import com.example.collegesecondhand_springboot.common.Result;
import com.example.collegesecondhand_springboot.entity.Admin;
import com.example.collegesecondhand_springboot.entity.Student;
import com.example.collegesecondhand_springboot.exception.ServiceException;
import com.example.collegesecondhand_springboot.service.AdminService;
import com.example.collegesecondhand_springboot.service.StudentService;
import com.example.collegesecondhand_springboot.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    //登录
    @GetMapping("/login")
    public Result login(@RequestParam String Susername, @RequestParam String Spassword){
        if(studentService.login(Susername,Spassword)!=null)
        {
            // 设置token
            String SuserId=studentService.findId(Susername);//根据用户名去查找ID
            String token = TokenUtils.genToken(SuserId, Spassword);
            Student student = studentService.login(Susername,Spassword);
            student.setToken(token);

            return Result.success(student);
        }
        else
        {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");//自定义异常
        }

    }

    //注册
    @PostMapping("/registerStudent")
    public Result register(@RequestParam String Susername, @RequestParam String Spassword){
        if(studentService.registerStudent(Susername,Spassword))
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
    public Result updatePassword(@RequestParam("Sid") String Sid,@RequestParam String Spassword, @RequestParam String Snewpassword){
        //为了将前端传来的string类型转化为数据库对应的int类型，做出如下操作
        Integer Iid = Integer.parseInt(Sid);

        if(studentService.updatePassword(Iid,Spassword, Snewpassword))
        {
            return Result.success("修改密码成功");
        }
        else
        {
            throw new ServiceException(Constants.CODE_600, "修改失败：密码错误");//自定义异常
        }
    }

    //查询
    @GetMapping("/getStudent")
    public List<Student> getStudent() {
        return studentService.findAll();
    }

    //根据id查询数据
    @GetMapping("/getOneStudent")
    public Student findOne(@RequestParam("Sid") String Sid)
    {
        Integer id = Integer.parseInt(Sid);
        return studentService.findOne(id);
    }

    //增加
    @PostMapping("/insertStudent")
    public Result addStudent(@RequestBody Student student) {
        if(studentService.insertStudent(student))
        {
            return Result.success("添加成功");
        }
        else
        {
            throw new ServiceException(Constants.CODE_600, "用户名重复");//自定义异常
        }

    }
    //修改
    @PutMapping("/updateStudent")
    public Result updateStudent(@RequestBody Student student){
        if(studentService.updateStudent(student))
        {
            return Result.success("修改成功");
        }
        else
        {
            throw new ServiceException(Constants.CODE_600, "用户名重复");//自定义异常
        }
    }
    //删除
    @DeleteMapping("/deleteStudent/{Sid}")
    public Integer deleteStudent(@PathVariable("Sid") Long Sid){
        return studentService.deleteStudent(Sid);
    }
    //批量删除
    @PostMapping("/deleteStudent/batch")
    public Integer deleteStudentBatch(@RequestBody List<Integer> Sids){
        return studentService.deleteStudentBatch(Sids);
    }

    //分页查询
    // 接口路径：/user/page?pageNum=1&pageSize=10
    // @RequestParam接受
    // limit第一个参数 = (pageNum - 1) * pageSize
    // pageSize
    @GetMapping("/getStudentPage")
    public Map<String, Object> getStudentPage(@RequestParam Integer pageNum,
                                           @RequestParam Integer pageSize,
                                           @RequestParam String Susername) {
        pageNum = (pageNum - 1) * pageSize;
        List<Student> data = studentService.getStudentPage(pageNum, pageSize, Susername);
        Integer total = studentService.getTotal(Susername);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }

    //查询总学生数
    @GetMapping("/getStudentAllTotal")
    public int getAllTotal(){
        return studentService.getAllTotal();
    }

}
