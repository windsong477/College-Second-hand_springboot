package com.example.collegesecondhand_springboot.controller;

import com.example.collegesecondhand_springboot.common.Constants;
import com.example.collegesecondhand_springboot.common.Result;
import com.example.collegesecondhand_springboot.entity.Goods;
import com.example.collegesecondhand_springboot.entity.Order;
import com.example.collegesecondhand_springboot.entity.Student;
import com.example.collegesecondhand_springboot.exception.ServiceException;
import com.example.collegesecondhand_springboot.service.GoodsService;
import com.example.collegesecondhand_springboot.service.OrderService;
import com.example.collegesecondhand_springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodsService goodsService;

    //查询
    @GetMapping("/getOrder")
    public List<Order> getOrder() {
        return orderService.findAll();
    }

    //查询
    @GetMapping("/getOneOrder")
    public Order getOneOrder(@RequestParam("Oid") String Oid) {
        return orderService.getOneOrder(Oid);
    }

    //根据买家id查询数据
    @GetMapping("/getOneOrderBUY")
    public Map<String, Object> findOneBUY(@RequestParam("BUYSid") String BUYSid,@RequestParam("Oid") String Oid){

        List<Order> data = orderService.findOneBUY(BUYSid,Oid);
        Integer total = orderService.getBUYTotal(BUYSid,Oid);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }

    //根据卖家id查询数据
    @GetMapping("/getOneOrderSELL")
    public Map<String, Object> findOneSELL(@RequestParam("SELLSid") String SELLSid,@RequestParam("Oid") String Oid){

        List<Order> data = orderService.findOneSELL(SELLSid,Oid);
        Integer total = orderService.getSELLTotal(SELLSid,Oid);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }

    //增加
    @PostMapping("/insertOrder")
    public Result addOrder(@RequestParam("BUYSid") String BUYSid,@RequestParam("SELLSid") String SELLSid,@RequestParam("Gid") String Gid,@RequestParam("Oprice") String Oprice) {
        if(orderService.insertOrder(BUYSid,SELLSid,Gid,Oprice))
        {
                return Result.success("交易成功");
        }
        else
        {
            throw new ServiceException(Constants.CODE_600, "后台异常");//自定义异常
        }

    }

    //删除
    @DeleteMapping("/deleteOrder/{Oid}")
    public Integer deleteOrder(@PathVariable("Oid") Long Oid){
        return orderService.deleteOrder(Oid);
    }
    //批量删除
    @PostMapping("/deleteOrder/batch")
    public Integer deleteOrderBatch(@RequestBody List<Integer> Oids){
        return orderService.deleteOrderBatch(Oids);
    }

    //分页查询
    // 接口路径：/user/page?pageNum=1&pageSize=10
    // @RequestParam接受
    // limit第一个参数 = (pageNum - 1) * pageSize
    // pageSize
    @GetMapping("/getOrderPage")
    public Map<String, Object> getOrderPage(@RequestParam Integer pageNum,
                                            @RequestParam Integer pageSize,
                                            @RequestParam String Oid) {
        pageNum = (pageNum - 1) * pageSize;

        List<Order> data = orderService.getOrderPage(pageNum, pageSize, Oid);
        Integer total = orderService.getTotal(Oid);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }

    //查询总订单数
    @GetMapping("/getOrderAllTotal")
    public int getAllTotal(){
        return orderService.getAllTotal();
    }

    //查询所有订单的总成交价格
    @GetMapping("/getOrderOpriceTotal")
    public String getOpriceTotal(){
        return orderService.getOpriceTotal();
    }
}
