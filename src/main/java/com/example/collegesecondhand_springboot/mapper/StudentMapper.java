package com.example.collegesecondhand_springboot.mapper;

import com.example.collegesecondhand_springboot.entity.Admin;
import com.example.collegesecondhand_springboot.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {
    //sql->登录查询
    @Select("SELECT * FROM student WHERE Susername=#{Susername} AND Spassword=#{Spassword}")
    Student login(@Param("Susername") String Susername, @Param("Spassword") String Spassword);

    //sql->注册
    @Insert("INSERT INTO student(Susername,Spassword) VALUES (#{Susername},#{Spassword})")
    Boolean registerStudent(@Param("Susername") String Susername,@Param("Spassword") String Spassword);

    //sql->查询
    @Select("SELECT * FROM student")
    List<Student> findAll();

    //sql->根据用户名查询id
    @Select("SELECT Sid FROM student WHERE Susername=#{Susername}")
    String findId(@Param("Susername") String Susername);

    //sql->根据id查询所有数据
    @Select("SELECT * FROM student WHERE Sid=#{Sid}")
    Student findOne(@Param("Sid") Integer Sid);

    //mybatis的xml方式用sql->插入
    Boolean insertStudent(Student student);

    //mybatis的xml方式用sql->修改
    Boolean updateStudent(Student student);

    //sql->修改个人密码
    @Update("UPDATE student SET Spassword=#{Snewpassword} WHERE Spassword=#{Spassword} AND Sid=#{Sid}")
    Boolean updatePassword(@Param("Sid") Integer Sid,@Param("Spassword") String Spassword,@Param("Snewpassword") String Anewpassword);

    //sql->删除
    @Delete("DELETE FROM student WHERE Sid=#{Sid}")
    Integer deleteStudent(long Sid);

    //mybatis的xml方式用sql->批量删除
    Integer deleteStudentBatch(List<Integer> Sids);

    //sql->分页查询
    @Select("SELECT *  FROM student WHERE (IFNULL(Susername,'') LIKE concat('%', #{Susername}, '%')) limit #{pageNum}, #{pageSize}")
    List<Student> getStudentPage(@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize,@Param("Susername") String Susername);

    //sql->查询所有数据量
    @Select("SELECT count(*) FROM student WHERE (IFNULL(Susername,'') LIKE concat('%', #{Susername}, '%'))")
    int getTotal(@Param("Susername") String Susername);

    //sql->查询学生总数
    @Select("SELECT COUNT(*) FROM student")
    int getAllTotal();
}
