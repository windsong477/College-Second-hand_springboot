package com.example.collegesecondhand_springboot.entity;

import lombok.Data;

import java.util.List;

@Data
public class Order {
    private Integer Oid;
    private Integer BUYSid;
    private Integer SELLSid;
    private Integer Gid;
    private String Oprice;
    private String Otime;

    //以下非数据库字段
    private String Susername;//来自student表
    private String SELLusername;//以下来自goods表
    private String Gname;
    private String Gphoto;
    private String Gdescribe;
}
