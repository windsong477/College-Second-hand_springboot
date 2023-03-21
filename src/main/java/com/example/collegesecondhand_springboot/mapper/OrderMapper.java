package com.example.collegesecondhand_springboot.mapper;


import com.example.collegesecondhand_springboot.entity.Goods;
import com.example.collegesecondhand_springboot.entity.Order;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface OrderMapper {

    //sql->查询
    @Select("SELECT * FROM `order`")
    List<Order> findAll();

    //sql->根据订单Oid查询所有数据(左外连接)
    @Select("SELECT `order`.*,goods.Gname,goods.SELLusername,goods.Gphoto,goods.Gdescribe,student.Susername FROM student,`order` LEFT JOIN goods ON `order`.Gid=goods.Gid WHERE `order`.Oid=#{Oid} AND `order`.BUYSid=student.Sid")
    Order getOneOrder(@Param("Oid") String Oid);

    //sql->根据订单id查询所有数据(数据库)
    @Select("SELECT * FROM `order` WHERE Oid=#{Oid}")
    Order findOne(@Param("Oid") String Oid);

    //sql->根据买家id查询所有数据
    @Select("SELECT `order`.*,goods.Gname,goods.SELLusername,goods.Gphoto,goods.Gdescribe,student.Susername FROM student,`order` LEFT JOIN goods ON `order`.Gid=goods.Gid WHERE (IFNULL(Oid,'') LIKE concat('%', #{Oid}, '%')) AND `order`.BUYSid=student.Sid AND `order`.BUYSid=#{BUYSid}")
    List<Order> findOneBUY(@Param("BUYSid") String BUYSid,@Param("Oid") String Oid);

    //sql->买家id查询所有数据量
    @Select("SELECT count(*) FROM `order` WHERE (Oid LIKE concat('%', #{Oid}, '%'))AND `order`.BUYSid=#{BUYSid}")
    int getBUYTotal(@Param("BUYSid") String BUYSid,@Param("Oid") String Oid);

    //sql->根据卖家id查询所有数据
    @Select("SELECT `order`.*,goods.Gname,goods.SELLusername,goods.Gphoto,goods.Gdescribe,student.Susername FROM student,`order` LEFT JOIN goods ON `order`.Gid=goods.Gid WHERE (IFNULL(Oid,'') LIKE concat('%', #{Oid}, '%')) AND `order`.SELLSid=student.Sid AND `order`.SELLSid=#{SELLSid}")
    List<Order> findOneSELL(@Param("SELLSid") String SELLSid,@Param("Oid") String Oid);

    //sql->卖家id查询所有数据量
    @Select("SELECT count(*) FROM `order` WHERE (Oid LIKE concat('%', #{Oid}, '%'))AND `order`.SELLSid=#{SELLSid}")
    int getSELLTotal(@Param("SELLSid") String SELLSid,@Param("Oid") String Oid);

    //sql->插入
    @Insert("INSERT INTO `order`(BUYSid,SELLSid,Gid,Oprice) VALUES (#{BUYSid},#{SELLSid},#{Gid},#{Oprice})")
    Boolean insertOrder(@Param("BUYSid") String BUYSid,@Param("SELLSid") String SELLSid,@Param("Gid") String Gid,@Param("Oprice") String Oprice);

    /*//mybatis的xml方式用sql->修改
    @Update("UPDATE order SET Ausername=#{Ausername},Aavatar=#{Aavatar},Aphone=#{Aphone} WHERE Aid=#{Aid}")
    Boolean updateAdmin(Order order);*/


    //sql->删除
    @Delete("DELETE FROM `order` WHERE Oid=#{Oid}")
    Integer deleteOrder(long Oid);

    //mybatis的xml方式用sql->批量删除
    Integer deleteOrderBatch(List<Integer> Oids);

    //sql->分页查询
    @Select("SELECT `order`.*,goods.Gname,goods.SELLusername,goods.Gphoto,goods.Gdescribe,student.Susername FROM student,`order` LEFT JOIN goods ON `order`.Gid=goods.Gid WHERE (IFNULL(Oid,'') LIKE concat('%', #{Oid}, '%')) AND `order`.BUYSid=student.Sid limit #{pageNum}, #{pageSize}")
    List<Order> getOrderPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("Oid") String Oid);

    //sql->查询所有数据量
    @Select("SELECT count(*) FROM `order` WHERE (Oid LIKE concat('%', #{Oid}, '%'))")
    int getTotal(@Param("Oid") String Oid);

    //sql->查询订单总数
    @Select("SELECT COUNT(*) FROM `order`")
    int getAllTotal();

    //sql->查询订单成交价总价格
    @Select("SELECT SUM(Oprice) FROM `order`")
    String getOpriceTotal();
}
