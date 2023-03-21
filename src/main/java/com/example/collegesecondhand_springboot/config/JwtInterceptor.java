package com.example.collegesecondhand_springboot.config;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.collegesecondhand_springboot.common.Constants;
import com.example.collegesecondhand_springboot.entity.Admin;
import com.example.collegesecondhand_springboot.entity.Student;
import com.example.collegesecondhand_springboot.exception.ServiceException;
import com.example.collegesecondhand_springboot.service.AdminService;
import com.example.collegesecondhand_springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        // 执行认证
        if (StrUtil.isBlank(token)) {
            throw new ServiceException(Constants.CODE_401, "您当前未登录，请重新登录！");
        }
        // 获取 token 中的 user id
        String id;
        try {
            id = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new ServiceException(Constants.CODE_401, "token验证失败，请重新登录");
        }
        // 根据token中的id查询数据库
        Admin admin = adminService.findOne(Integer.parseInt(id));
        Student student = studentService.findOne(Integer.parseInt(id));
        if (admin == null&&student == null) {  //两个都没有token
            throw new ServiceException(Constants.CODE_401, "用户不存在，请重新登录");
        }
        if(admin!=null&&student==null)         //admin中的token
        {
            // 用户密码加签验证 token
            JWTVerifier jwtVerifier_admin = JWT.require(Algorithm.HMAC256(admin.getApassword())).build();
            try {
                jwtVerifier_admin.verify(token); // 验证token
            } catch (JWTVerificationException e) {
                throw new ServiceException(Constants.CODE_401, "token验证失败，请重新登录");
            }
        }
        else if(admin==null&&student!=null)     //student中的token
        {
            JWTVerifier jwtVerifier_student = JWT.require(Algorithm.HMAC256(student.getSpassword())).build();
            try {
                jwtVerifier_student.verify(token); // 验证token
            } catch (JWTVerificationException e) {
                throw new ServiceException(Constants.CODE_401, "token验证失败，请重新登录");
            }
        }


        return true;
    }
}
