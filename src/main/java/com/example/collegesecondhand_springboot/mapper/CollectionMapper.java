package com.example.collegesecondhand_springboot.mapper;


import com.example.collegesecondhand_springboot.entity.Collection;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CollectionMapper {

    //sql->根据学生id查询数据
    @Select("SELECT * FROM collection LEFT JOIN goods ON collection.Gid=goods.Gid  WHERE collection.Sid=#{Sid} AND (IFNULL(goods.Gname,'') LIKE concat('%', #{Gname}, '%')) ORDER BY Ctime DESC")
    List<Collection> findCollection(@Param("Sid") String Sid,@Param("Gname") String Gname);

    //sql->查询Sid所有数据量
    @Select("SELECT count(*) FROM collection LEFT JOIN goods ON collection.Gid=goods.Gid  WHERE collection.Sid=#{Sid} AND (IFNULL(goods.Gname,'') LIKE concat('%', #{Gname}, '%'))")
    int getSidTotal(@Param("Sid") String Sid,@Param("Gname") String Gname);

    //sql->根据学生id和商品id查询数据
    @Select("SELECT * FROM collection WHERE Sid=#{Sid} AND Gid=#{Gid}")
    Boolean findMyCollection(@Param("Sid") String Sid,@Param("Gid") String Gid);

    //sql->插入
    @Insert("INSERT INTO collection(Sid,Gid) VALUES (#{Sid},#{Gid})")
    Boolean insertCollection(@Param("Sid") String Sid,@Param("Gid") String Gid);

    //sql->删除
    @Delete("DELETE FROM collection WHERE Sid=#{Sid} AND Gid=#{Gid}")
    Integer deleteCollection(@Param("Sid") long Sid,@Param("Gid") long Gid);
}
