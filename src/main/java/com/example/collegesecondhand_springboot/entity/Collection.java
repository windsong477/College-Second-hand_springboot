package com.example.collegesecondhand_springboot.entity;

import lombok.Data;

@Data
public class Collection {
    private Integer Sid;
    private Integer Gid;

    //以下非数据库字段
    private String Gname;//来自goods表
    private String Gbuyprice;
    private String Gsellprice;
    private String Gphoto;
    private String Gdescribe;
    private String Gaudit;
    private String Gstatus;
    private String SELLusername;
}
