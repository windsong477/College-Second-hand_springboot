package com.example.collegesecondhand_springboot.mapper;

import com.example.collegesecondhand_springboot.entity.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodsMapper {

    //sql->查询
    @Select("SELECT * FROM goods")
    List<Goods> findAll();

    //sql->根据Sid查询数据
    @Select("SELECT * FROM goods WHERE Sid=#{Sid} AND (IFNULL(Gstatus,'') LIKE concat('%', #{Gstatus}, '%')) AND (IFNULL(Gname,'') LIKE concat('%', #{Gname}, '%')) ORDER BY Gtime DESC")
    List<Goods> getSidGoods(@Param("Sid") String Sid,@Param("Gstatus") String Gstatus,@Param("Gname") String Gname);

    //sql->根据id查询所有数据
    @Select("SELECT * FROM goods WHERE Gid=#{Gid}")
    Goods findOne(@Param("Gid") Integer Gid);

    //sql->插入(只能由学生用户去添加商品数据)
    @Insert("INSERT INTO goods(Sid,Gname,Gbuyprice,Gsellprice,Gphoto,Gdescribe,SELLusername) VALUES (#{Sid},#{Gname},#{Gbuyprice},#{Gsellprice},#{Gphoto},#{Gdescribe},#{SELLusername})")
    Boolean insertGoods(Goods goods);

    //sql->修改商品状态(学生用户修改商品状态)
    @Update("UPDATE goods SET Gstatus=#{Gstatus} WHERE Gid=#{Gid}")
    Boolean updateGoodsGstatus(@Param("Gid") String Gid,@Param("Gstatus") String Gstatus);

    //sql->修改(管理员修改商品信息)
    /*@Update("UPDATE goods SET Gname=#{Gname},Gbuyprice=#{Gbuyprice},Gsellprice=#{Gsellprice},Gphoto=#{Gphoto},Gdescribe=#{Gdescribe} WHERE Gid=#{Gid}")*/
    Boolean updateGoods(Goods goods);

    //sql->修改(学生用户修改商品信息)
    Boolean updateStudentGoods(Goods goods);

    //sql->删除
    @Delete("DELETE FROM goods WHERE Gid=#{Gid}")
    Integer deleteGoods(long Gid);

    //mybatis的xml方式用sql->批量删除
    Integer deleteGoodsBatch(List<Integer> Gids);

    //sql->学生分页查询
    @Select("SELECT *  FROM goods WHERE (IFNULL(Gname,'') LIKE concat('%', #{Gname}, '%')) AND Gaudit='1' ORDER BY Gtime DESC limit #{pageNum}, #{pageSize} ")
    List<Goods> getGoodsPage(@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize,@Param("Gname") String Gname);

    //sql->查询Sid所有数据量
    @Select("SELECT count(*) FROM goods WHERE Sid=#{Sid} AND (IFNULL(Gstatus,'') LIKE concat('%', #{Gstatus}, '%')) AND (IFNULL(Gname,'') LIKE concat('%', #{Gname}, '%'))")
    int getSidTotal(@Param("Sid") String Sid,@Param("Gstatus") String Gstatus,@Param("Gname") String Gname);

    //sql->学生查询所有数据量
    @Select("SELECT count(*) FROM goods WHERE (IFNULL(Gname,'') LIKE concat('%', #{Gname}, '%')) AND Gaudit='1'")
    int getTotal(@Param("Gname") String Gname);

    //sql->查询商品总数
    @Select("SELECT COUNT(*) FROM goods")
    int getAllTotal();

    //sql->管理员分页查询
    @Select("SELECT *  FROM goods WHERE (IFNULL(Gname,'') LIKE concat('%', #{Gname}, '%')) ORDER BY Gtime DESC limit #{pageNum}, #{pageSize} ")
    List<Goods> getAdminGoodsPage(@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize,@Param("Gname") String Gname);

    //sql->管理员查询所有数据量
    @Select("SELECT count(*) FROM goods WHERE (IFNULL(Gname,'') LIKE concat('%', #{Gname}, '%'))")
    int getAdminTotal(@Param("Gname") String Gname);
}
