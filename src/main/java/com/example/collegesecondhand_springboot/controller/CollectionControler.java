package com.example.collegesecondhand_springboot.controller;


import com.example.collegesecondhand_springboot.common.Constants;
import com.example.collegesecondhand_springboot.common.Result;

import com.example.collegesecondhand_springboot.entity.Collection;
import com.example.collegesecondhand_springboot.entity.Goods;
import com.example.collegesecondhand_springboot.exception.ServiceException;
import com.example.collegesecondhand_springboot.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/collection")
public class CollectionControler {
    @Autowired
    CollectionService collectionService;


    //根据学生id查询数据
    @GetMapping("/getCollection")
    public Map<String, Object> findCollection(@RequestParam("Sid") String Sid,@RequestParam("Gname") String Gname){

        List<Collection> data = collectionService.findCollection(Sid,Gname);
        Integer total = collectionService.getSidTotal(Sid,Gname);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;

    }

    //根据学生id和商品id查询数据
    @GetMapping("/getMyCollection")
    public Boolean findMyCollection(@RequestParam("Sid") String Sid,@RequestParam("Gid") String Gid){
        return collectionService.findMyCollection(Sid,Gid);
    }

    //增加
    @PostMapping("/insertCollection")
    public Result addCollection(@RequestParam("Sid") String Sid,@RequestParam("Gid") String Gid) {
        if(collectionService.insertCollection(Sid,Gid))
        {
            return Result.success("收藏成功");
        }
        else
        {
            throw new ServiceException(Constants.CODE_600, "后台异常");//自定义异常
        }

    }

    //删除
    @DeleteMapping("/deleteCollection/{Sid}&{Gid}")
    public Integer deleteCollection(@PathVariable("Sid") Long Sid,@PathVariable("Gid") Long Gid){
        return collectionService.deleteCollection(Sid,Gid);
    }
}
