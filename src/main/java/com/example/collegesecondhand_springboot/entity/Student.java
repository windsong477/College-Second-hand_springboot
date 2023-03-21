package com.example.collegesecondhand_springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;



@Data
public class Student {
    private String Sid;
    private String Susername;
    @JsonIgnore           //此注解可忽略后端传去给前端的值
    private String Spassword;
    private String Sschool;
    private String Scollege;
    private String Sgrade;
    private String Sclass;
    private String Smajor;
    private String Sno;
    private String Ssex;
    private String Sqq;
    private String Swx;
    private String Sphone;
    private String Sadress;
    private String Savatar;
    private String Stime;

    //以下非数据库字段,也可新建一个DTO类，copy到此实体类
    private String token;
    @JsonIgnore
    private String newpassword;
}
