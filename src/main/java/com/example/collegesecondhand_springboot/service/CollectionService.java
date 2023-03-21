package com.example.collegesecondhand_springboot.service;

import com.example.collegesecondhand_springboot.entity.Admin;
import com.example.collegesecondhand_springboot.entity.Collection;
import com.example.collegesecondhand_springboot.mapper.CollectionMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionService {

    @Autowired
    CollectionMapper collectionMapper;

    //根据学生id查找数据
    public List<Collection> findCollection(String Sid,String Gname){
        return collectionMapper.findCollection(Sid,Gname);
    }

    //查询Sid的所有数据量
    public int getSidTotal(String Sid,String Gname){
        return collectionMapper.getSidTotal(Sid,Gname);
    }

    //根据学生id和商品id查找数据
    public Boolean findMyCollection(@Param("Sid") String Sid,@Param("Gid") String Gid){
        if(collectionMapper.findMyCollection(Sid,Gid)!=null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //插入
    public Boolean insertCollection(@Param("Sid") String Sid,@Param("Gid") String Gid){
        try {
            return collectionMapper.insertCollection(Sid,Gid);
        }catch (Exception e)
        {
            return false;
        }
    }

    //删除
    public Integer deleteCollection(long Sid,long Gid){
        return collectionMapper.deleteCollection(Sid,Gid);
    }
}
