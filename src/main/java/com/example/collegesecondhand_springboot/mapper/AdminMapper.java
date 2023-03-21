package com.example.collegesecondhand_springboot.mapper;


import com.example.collegesecondhand_springboot.entity.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {

    //sql->登录查询
    @Select("SELECT * FROM admin WHERE Ausername=#{Ausername} AND Apassword=#{Apassword}")
    Admin login(@Param("Ausername") String Ausername, @Param("Apassword") String Apassword);

    //sql->注册
    @Insert("INSERT INTO admin(Ausername,Apassword) VALUES (#{Ausername},#{Apassword})")
    Boolean registerAdmin(@Param("Ausername") String Ausername,@Param("Apassword") String Apassword);

    //sql->查询
    @Select("SELECT * FROM admin")
    List<Admin> findAll();

    //sql->根据用户名查询id
    @Select("SELECT Aid FROM admin WHERE Ausername=#{Ausername}")
    String findId(@Param("Ausername") String Ausername);

    //sql->根据id查询所有数据
    @Select("SELECT * FROM admin WHERE Aid=#{Aid}")
    Admin findOne(@Param("Aid") Integer Aid);

    //sql->插入
    @Insert("INSERT INTO admin(Ausername,Aphone) VALUES (#{Ausername},#{Aphone})")
    Boolean insertAdmin(Admin admin);

    //mybatis的xml方式用sql->修改
    @Update("UPDATE admin SET Ausername=#{Ausername},Aavatar=#{Aavatar},Aphone=#{Aphone} WHERE Aid=#{Aid}")
    Boolean updateAdmin(Admin admin);

    //sql->修改个人密码
    @Update("UPDATE admin SET Apassword=#{Anewpassword} WHERE Apassword=#{Apassword} AND Aid=#{Aid}")
    Boolean updatePassword(@Param("Aid") Integer Aid,@Param("Apassword") String Apassword,@Param("Anewpassword") String Anewpassword);

    //sql->删除
    @Delete("DELETE FROM admin WHERE Aid=#{Aid}")
    Integer deleteAdmin(long Aid);

    //mybatis的xml方式用sql->批量删除
    Integer deleteAdminBatch(List<Integer> Aids);

    //sql->分页查询
    @Select("SELECT *  FROM admin WHERE (IFNULL(Ausername,'') LIKE concat('%', #{Ausername}, '%')) limit #{pageNum}, #{pageSize}")
    List<Admin> getAdminPage(@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize,@Param("Ausername") String Ausername);

    //sql->根据用户名查询所有数据量
    @Select("SELECT count(*) FROM admin WHERE (IFNULL(Ausername,'') LIKE concat('%', #{Ausername}, '%'))")
    int getTotal(@Param("Ausername") String Ausername);

    //sql->查询管理员总数
    @Select("SELECT COUNT(*) FROM admin")
    int getAllTotal();
}
