package com.yyjccc.studentsystem.Controller;

import Mode.enity.Student;
import com.yyjccc.studentsystem.Service.Students;
import com.yyjccc.studentsystem.Utils.FilterUtils;
import com.yyjccc.studentsystem.Utils.Response.Message;
import com.yyjccc.studentsystem.Utils.Response.msgCode;
import com.yyjccc.studentsystem.Utils.ResponseBase;
import com.yyjccc.studentsystem.Utils.ResponseUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1")
@RestController
@RequestMapping("/api/students")
public class StudentsController  {
    private static ResponseBase data;

    protected static String [] allowTypes=new String[]{"name","sex","classId","department","birthday","nativePlace"};

    @GetMapping("/getAll")
    public String getallStudents(){
        ResponseBase data=null;
        try {
             data=new ResponseBase();
             List<Student> queryData=Students.getAll();
            data.setInfo(new Message <List<Student>>(1,queryData));
        }catch (Exception e){
            data = ResponseBase.failBuild(msgCode.DatabaseError);
        }
        return ResponseUtils.toJson(data);
    }

    @PostMapping("/query")
    public String query(@RequestParam String type,@RequestParam String value){
        ResponseBase data=null;
        String [] allowType=new String[]{"StudentId","name","sex","classId","department","birthday","nativePlace"};
        if(FilterUtils.argFilter(type,allowType)){
            try{
                data=new ResponseBase();
                data.setInfo(new Message< List<Student>>(1,Students.queryByType(type,value)));
            }catch(Exception e){
                data = ResponseBase.failBuild(msgCode.MSG_CODE);
            }
        }else {
            data = ResponseBase.failBuild(msgCode.MSG_CODE);
        }

        return ResponseUtils.toJson(data);
    }

    @PostMapping("/update")
    public String update(@RequestParam long StudentId,@RequestParam String type,@RequestParam String value){
        ResponseBase data=null;
        if(FilterUtils.argFilter(type,allowTypes)){
            try{

                if(Students.updateByType(StudentId,type,value)){
                    data=new ResponseBase();
                }else {
                    data = ResponseBase.failBuild(msgCode.MSG_CODE);
                }

            }catch (Exception e){
                data = ResponseBase.failBuild(msgCode.MSG_CODE);
            }
        }else {
            data = ResponseBase.failBuild(msgCode.MSG_CODE);
        }
        return ResponseUtils.toJson(data);
    }
    @PostMapping("/delete")
    public String delete(@RequestParam long StudentId) throws IOException {
        ResponseBase data=null;
        if (StudentId!=0){
            List<Student> checklist=Students.queryByType("StudentId",Long.toString(StudentId));
            if(!checklist.isEmpty()){
                try {

                    if(Students.deleteData(StudentId)){

                        data=new ResponseBase();
                    }else {
                        data = ResponseBase.failBuild(msgCode.MSG_CODE);
                    }
                }catch (Exception e){
                    data = ResponseBase.failBuild(msgCode.MSG_CODE);
                }}else {
                data = ResponseBase.failBuild(msgCode.No_data);
            }
        }else {
            data = ResponseBase.failBuild(msgCode.NO_Args);
        }
        return ResponseUtils.toJson(data);
    }


    //接收参数是一个javaBean
    @PostMapping("/insert")
    public String insert(@ModelAttribute Student student){
        ResponseBase data=null;
        if(student.getStudentId()!=0 && student.getName()!=null){
            try {
               if(Students.insertData(student)){
                   data=new ResponseBase();
               }else {
                   data=ResponseBase.failBuild(msgCode.DatabaseError);
               }

            }catch (Exception e){
                data = ResponseBase.failBuild(msgCode.MSG_CODE);
            }
        }else {

            data = ResponseBase.failBuild(msgCode.NO_Args);
        }
        return ResponseUtils.toJson(data);
    }
}
