package com.yyjccc.studentsystem.Controller;

import Mode.Mapper.CodeMapper;
import Mode.enity.Code;
import com.yyjccc.studentsystem.Utils.Datautils;
import com.yyjccc.studentsystem.Utils.Response.Message;
import com.yyjccc.studentsystem.Utils.ResponseBase;
import com.yyjccc.studentsystem.Utils.ResponseUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController
public class CodeServices {

    @GetMapping("/api/code/changes")
    public String getmychangecode() throws IOException {
        ResponseBase data=new ResponseBase();
        SqlSession sqlSession= Datautils.SqlStart();
        CodeMapper mapper=sqlSession.getMapper(CodeMapper.class);
        List<Code> res=mapper.changeCodes();
        data.setInfo(new Message<List<Code>>(1,res));
        sqlSession.close();
        return ResponseUtils.toJson(data);

    }

    @GetMapping("/api/code/reward")
    public String getrewardcode() throws IOException {
        ResponseBase data=new ResponseBase();
        SqlSession sqlSession= Datautils.SqlStart();
        CodeMapper mapper=sqlSession.getMapper(CodeMapper.class);
        List<Code> res=mapper.rewardCode();
        data.setInfo(new Message<List<Code>>(1,res));
        sqlSession.close();
        return ResponseUtils.toJson(data);

    }

    @GetMapping("/api/code/punishment")
    public String getpunishmentcode() throws IOException {
        ResponseBase data=new ResponseBase();
        SqlSession sqlSession= Datautils.SqlStart();
        CodeMapper mapper=sqlSession.getMapper(CodeMapper.class);
        List<Code> res=mapper.punishmentCode();
        data.setInfo(new Message<List<Code>>(1,res));
        sqlSession.close();
        return ResponseUtils.toJson(data);

    }

    @GetMapping("/api/code/class")
    public String getclasscode() throws IOException {
        ResponseBase data=new ResponseBase();
        SqlSession sqlSession= Datautils.SqlStart();
        CodeMapper mapper=sqlSession.getMapper(CodeMapper.class);
        List<Code> res=mapper.classCode();
        data.setInfo(new Message<List<Code>>(1,res));
        sqlSession.close();
        return ResponseUtils.toJson(data);

    }

    @GetMapping("/api/code/change")
    public String getdepartmentcode() throws IOException {
        ResponseBase data=new ResponseBase();
        SqlSession sqlSession= Datautils.SqlStart();
        CodeMapper mapper=sqlSession.getMapper(CodeMapper.class);
        List<Code> res=mapper.departmentCode();
        data.setInfo(new Message<List<Code>>(1,res));
        sqlSession.close();
        return ResponseUtils.toJson(data);

    }


}
