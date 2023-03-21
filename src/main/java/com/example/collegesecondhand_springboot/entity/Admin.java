package com.example.collegesecondhand_springboot.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;



@Data
public class Admin {
    private Integer Aid;
    private String Ausername;
    @JsonIgnore           //此注解可忽略后端传去给前端的值
    private String Apassword;
    private String Aphone;
    private String Aavatar;
    private String Atime;

    //以下非数据库字段,也可新建一个DTO类，copy到此实体类
    private String token;
    @JsonIgnore
    private String newpassword;


}
