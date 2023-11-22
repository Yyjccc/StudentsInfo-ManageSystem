package com.yyjccc.studentsystem.Service;

import Mode.Mapper.StudentsMapper;
import Mode.enity.Student;
import com.yyjccc.studentsystem.Utils.Datautils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class Students {





    public static List<Student> getAll() throws IOException {
        SqlSession sqlSession = Datautils.SqlStart();
        StudentsMapper  mapper= sqlSession.getMapper(StudentsMapper.class);
        List<Student> result=mapper.getAll();
        sqlSession.close();
        return result;
    }

    public static  List<Student> queryByType(String type,String value) throws IOException {
        SqlSession sqlSession = Datautils.SqlStart();
        StudentsMapper  mapper= sqlSession.getMapper(StudentsMapper.class);
        List<Student> result=mapper.query(type,value);
        sqlSession.close();
        return result;
    }


    public static boolean updateByType(long StudentId,String type,String value) throws IOException {
        SqlSession sqlSession = Datautils.SqlStart();
        StudentsMapper  mapper= sqlSession.getMapper(StudentsMapper.class);
        Boolean result= mapper.update(StudentId,type,value);
        sqlSession.close();
        return result;
    }

    public static boolean insertData(Student student) throws IOException {
        SqlSession sqlSession = Datautils.SqlStart();
        StudentsMapper  mapper= sqlSession.getMapper(StudentsMapper.class);
        Boolean result= mapper.insert(student);
        sqlSession.close();
        return result;
    }
    public static boolean deleteData(Long StudentId) throws IOException {
        SqlSession sqlSession = Datautils.SqlStart();
        StudentsMapper  mapper= sqlSession.getMapper(StudentsMapper.class);
        Boolean result= mapper.delete(StudentId);
        sqlSession.close();
        return result;
    }
}
