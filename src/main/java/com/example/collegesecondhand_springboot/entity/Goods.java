package com.example.collegesecondhand_springboot.entity;

import lombok.Data;

@Data
public class Goods {
    private Integer Gid;
    private Integer Sid;
    private String Gname;
    private String Gbuyprice;
    private String Gsellprice;
    private String Gphoto;
    private String Gdescribe;
    private String Gaudit;
    private String Gstatus;
    private String SELLusername;
    private String Gtime;
    private String Gupdate_time;
}
