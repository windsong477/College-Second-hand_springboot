package com.example.collegesecondhand_springboot.service;

import com.example.collegesecondhand_springboot.entity.Goods;
import com.example.collegesecondhand_springboot.entity.Order;

import com.example.collegesecondhand_springboot.mapper.OrderMapper;
import org.apache.ibatis.annotations.Param;
import org.ietf.jgss.Oid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    //查询
    public List<Order> findAll(){
        return orderMapper.findAll();
    }

    //根据订单Oid查找所有数据(左外连接)
    public Order getOneOrder(String Oid){
        return orderMapper.getOneOrder(Oid);
    }

    //根据订单id查找所有数据(数据库的)
    public Order findOne(@Param("Oid") String Oid){
        return orderMapper.findOne(Oid);
    }

    //根据买家id查找所有数据
    public List<Order> findOneBUY(String BUYSid,String Oid){
        return orderMapper.findOneBUY(BUYSid,Oid);
    }
    //买家查询所有数据量
    public int getBUYTotal(String BUYSid,String Oid){
        return orderMapper.getBUYTotal(BUYSid,Oid);
    }

    //根据卖家id查找所有数据
    public List<Order> findOneSELL(String SELLSid,String Oid){
        return orderMapper.findOneSELL(SELLSid,Oid);
    }
    //买家查询所有数据量
    public int getSELLTotal(String SELLSid,String Oid){
        return orderMapper.getSELLTotal(SELLSid,Oid);
    }

    //插入
    public Boolean insertOrder(String BUYSid,String SELLSid,String Gid,String Oprice){
        try {
            return orderMapper.insertOrder(BUYSid,SELLSid,Gid,Oprice);
        }catch (Exception e)
        {
            return false;
        }
    }

    //删除
    public Integer deleteOrder(long Oid){
        return orderMapper.deleteOrder(Oid);
    }

    //批量删除
    public Integer deleteOrderBatch(List<Integer> Oids){
        return orderMapper.deleteOrderBatch(Oids);
    }

    //分页查询
    public List<Order> getOrderPage(Integer pageNum, Integer pageSize, String Oid){
        return orderMapper.getOrderPage(pageNum, pageSize, Oid);
    }

    //查询所有数据量
    public int getTotal(String Oid){
        return orderMapper.getTotal(Oid);
    }

    //查询订单总数
    public int getAllTotal(){
        return orderMapper.getAllTotal();
    }

    //查询订单总数
    public String getOpriceTotal(){
        return orderMapper.getOpriceTotal();
    }
}
