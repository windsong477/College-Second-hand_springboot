package com.example.collegesecondhand_springboot.mapper;

import com.example.collegesecondhand_springboot.entity.Goods;
import com.example.collegesecondhand_springboot.entity.Leave;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LeaveMapper {

    //sql->插入
    @Insert("INSERT INTO `leave`(Sid,Gid,Lmessage) VALUES (#{Sid},#{Gid},#{Lmessage})")
    Boolean insertLeave(@Param("Sid") String Sid,@Param("Gid") String Gid,@Param("Lmessage") String Lmessage);

    //sql->删除
    @Delete("DELETE FROM `leave` WHERE Lid=#{Lid} AND Sid=#{Sid}")
    Integer deleteLeave(@Param("Lid") long Lid,@Param("Sid") long Sid);

    //sql->根据Gid查询数据
    //sql->商品留言分页查询
    @Select("SELECT *  FROM `leave` LEFT JOIN student ON `leave`.Sid=student.Sid  WHERE `leave`.Gid=#{Gid}  limit #{pageNum}, #{pageSize} ")
    List<Leave> getLeavePage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("Gid") String Gid);

    //sql->查询Gid所有数据量
    @Select("SELECT count(*) FROM `leave` WHERE Gid=#{Gid} ")
    int getGidTotal(@Param("Gid") String Gid);


    //sql->根据Sid查询数据
    //sql->商品留言分页查询
    @Select("SELECT *  FROM `leave` LEFT JOIN student ON `leave`.Sid=student.Sid  WHERE `leave`.Sid=#{Sid}  limit #{pageNum}, #{pageSize} ")
    List<Leave> getLeavePageSid(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("Sid") String Sid);

    //sql->查询Gid所有数据量
    @Select("SELECT count(*) FROM `leave` WHERE Sid=#{Sid} ")
    int getSidTotal(@Param("Sid") String Sid);
}
