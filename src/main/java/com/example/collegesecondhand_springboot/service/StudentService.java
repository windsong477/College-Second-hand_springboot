package com.example.collegesecondhand_springboot.service;

import com.example.collegesecondhand_springboot.entity.Admin;
import com.example.collegesecondhand_springboot.entity.Student;
import com.example.collegesecondhand_springboot.mapper.AdminMapper;
import com.example.collegesecondhand_springboot.mapper.StudentMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    //登录
    public Student login(String Susername, String Spassword){
        return studentMapper.login(Susername,Spassword);
    }

    //注册
    public Boolean registerStudent(@Param("Susername") String Susername, @Param("Spassword") String Spassword){
        try {
            return studentMapper.registerStudent(Susername,Spassword);
        }catch (Exception e)
        {
            return false;
        }

    }

    //查询
    public List<Student> findAll(){
        return studentMapper.findAll();
    }

    //根据用户名查找ID
    public String findId(@Param("Susername") String Susername){
        return studentMapper.findId(Susername);
    }

    //根据id查找所有数据
    public Student findOne(@Param("Sid") Integer Sid){
        return studentMapper.findOne(Sid);
    }

    //插入
    public Boolean insertStudent(Student student){
        try {
            return studentMapper.insertStudent(student);
        }catch (Exception e)
        {
            return false;
        }
    }

    //修改
    public Boolean updateStudent(Student student){
        try {
            return studentMapper.updateStudent(student);
        }catch (Exception e)
        {
            return false;

        }

    }

    //修改个人密码
    public Boolean updatePassword(Integer Sid,String Spassword,String Snewpassword){
        try {
            return studentMapper.updatePassword(Sid,Spassword, Snewpassword);
        }catch (Exception e)
        {
            return false;

        }

    }

    //删除
    public Integer deleteStudent(long Sid){
        return studentMapper.deleteStudent(Sid);
    }

    //批量删除
    public Integer deleteStudentBatch(List<Integer> Sids){
        return studentMapper.deleteStudentBatch(Sids);
    }

    //分页查询
    public List<Student> getStudentPage(Integer pageNum,Integer pageSize,String Susername){
        return studentMapper.getStudentPage(pageNum, pageSize, Susername);
    }

    //查询所有数据量
    public int getTotal(String Susername){
        return studentMapper.getTotal(Susername);
    }

    //查询学生总数
    public int getAllTotal(){
        return studentMapper.getAllTotal();
    }
}
