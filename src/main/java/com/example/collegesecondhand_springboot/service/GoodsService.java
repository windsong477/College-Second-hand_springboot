package com.example.collegesecondhand_springboot.service;

import com.example.collegesecondhand_springboot.entity.Admin;
import com.example.collegesecondhand_springboot.entity.Goods;
import com.example.collegesecondhand_springboot.mapper.AdminMapper;
import com.example.collegesecondhand_springboot.mapper.GoodsMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;


    //查询
    public List<Goods> findAll(){
        return goodsMapper.findAll();
    }

    //根据sid查询数据
    public List<Goods> getSidGoods(String Sid,String Gstatus,String Gname){
        return goodsMapper.getSidGoods(Sid,Gstatus,Gname);
    }

    //根据id查找所有数据
    public Goods findOne(@Param("Gid") Integer Gid){
        return goodsMapper.findOne(Gid);
    }

    //插入
    public Boolean insertGoods(Goods goods){
        try {
            return goodsMapper.insertGoods(goods);
        }catch (Exception e)
        {
            return false;
        }
    }

    //修改商品状态
    public Boolean updateGoodsGstatus(String Gid,String Gstatus){
        try {
            return goodsMapper.updateGoodsGstatus(Gid,Gstatus);
        }catch (Exception e)
        {
            return false;

        }

    }

    //管理员修改
    public Boolean updateGoods(Goods goods){
        try {
            return goodsMapper.updateGoods(goods);
        }catch (Exception e)
        {
            return false;

        }

    }

    //学生用户修改
    public Boolean updateStudentGoods(Goods goods){
        try {
            return goodsMapper.updateStudentGoods(goods);
        }catch (Exception e)
        {
            return false;

        }

    }

    //删除
    public Integer deleteGoods(long Gid){
        return goodsMapper.deleteGoods(Gid);
    }

    //批量删除
    public Integer deleteGoodsBatch(List<Integer> Gids){
        return goodsMapper.deleteGoodsBatch(Gids);
    }

    //学生分页查询
    public List<Goods> getGoodsPage(Integer pageNum,Integer pageSize,String Gname){
        return goodsMapper.getGoodsPage(pageNum, pageSize, Gname);
    }

    //查询Sid的所有数据量
    public int getSidTotal(String Sid,String Gstatus,String Gname){
        return goodsMapper.getSidTotal(Sid,Gstatus,Gname);
    }

    //查询所有数据量
    public int getTotal(String Gname){
        return goodsMapper.getTotal(Gname);
    }

    //查询商品总数
    public int getAllTotal(){
        return goodsMapper.getAllTotal();
    }

    //管理员分页查询
    public List<Goods> getAdminGoodsPage(Integer pageNum,Integer pageSize,String Gname){
        return goodsMapper.getAdminGoodsPage(pageNum, pageSize, Gname);
    }

    //管理员查询所有数据量
    public int getAdminTotal(String Gname){
        return goodsMapper.getAdminTotal(Gname);
    }
}
