package com.yyjccc.studentsystem.Controller;



import Mode.enity.Action.Change;
import Mode.enity.Action.Punishment;
import Mode.enity.Action.Reward;
import com.yyjccc.studentsystem.Service.Actions;
import com.yyjccc.studentsystem.Utils.FilterUtils;
import com.yyjccc.studentsystem.Utils.Response.Message;
import com.yyjccc.studentsystem.Utils.Response.msgCode;
import com.yyjccc.studentsystem.Utils.ResponseBase;
import com.yyjccc.studentsystem.Utils.ResponseUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/action")
public class ActionController  {



    private static String[] allowType={"StudentId","id","recTime","levels","description","enable","changeId"};

    private static ResponseBase data;
    private static String[] tableType=new String[]{"change","reward","punishment"};



    @PostMapping("/getAll")
    public String getAll(@RequestParam String ActionType)  {

         if(FilterUtils.argFilter(ActionType,tableType)){

            try {
                data=new ResponseBase<>();
                data.setInfo(new Message<List>(1,Actions.getAll(ActionType)));
            }catch (Exception e){
                e.printStackTrace();
                data = ResponseBase.failBuild(msgCode.DatabaseError);
            }
         }else {
             data = ResponseBase.failBuild(msgCode.Vaule_Error);
         }
        return ResponseUtils.toJson(data);
    }

    @PostMapping("/query")
    public String query(@RequestParam String ActionType, @RequestParam String type,@RequestParam String value){
        ResponseBase data=null;

        if(FilterUtils.argFilter(type,allowType)){
            try{
                data=new ResponseBase();
                data.setInfo(new Message<Object>(1, Actions.query(ActionType,type,value)));
            }catch(Exception e){
                e.printStackTrace();
                data = ResponseBase.failBuild(msgCode.DatabaseError);
            }
        }else {
            data = ResponseBase.failBuild(msgCode.MSG_CODE);
        }

        return ResponseUtils.toJson(data);
    }

    @PostMapping("/update")
    public String update(@RequestParam String ActionType, @RequestParam int id,@RequestParam String type,@RequestParam String value){
        ResponseBase data=null;
        if(FilterUtils.argFilter(type,allowType)){
            try{
                data=new ResponseBase();
                if(Actions.update(ActionType,id,type,value)){
                    data=new ResponseBase();
                }else {
                    data = ResponseBase.failBuild(msgCode.MSG_CODE);
                }

            }catch (Exception e){
                e.printStackTrace();
                data = ResponseBase.failBuild(msgCode.DatabaseError);
            }
        }else {
            data = ResponseBase.failBuild(msgCode.MSG_CODE);
        }
        return ResponseUtils.toJson(data);
    }
    @PostMapping("/delete")
    public String delete(@RequestParam String ActionType, @RequestParam int id) throws IOException {
        ResponseBase data=null;
        if (id!=0){
            List checklist=Actions.query(ActionType,"id",Integer.toString(id));
            if(!checklist.isEmpty()){
                try {

                    if(Actions.delete(ActionType,id)){

                        data=new ResponseBase();
                    }else {
                        data = ResponseBase.failBuild(msgCode.MSG_CODE);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    data = ResponseBase.failBuild(msgCode.DatabaseError);
                }}else {
                data = ResponseBase.failBuild(msgCode.No_data);
            }
        }else {
            data = ResponseBase.failBuild(msgCode.NO_Args);
        }
        return ResponseUtils.toJson(data);
    }


    //接收参数是一个javaBean
    @PostMapping("/insert/change")
    public String insert(@ModelAttribute Change change){
        ResponseBase data=null;
        if(change.getStudentId()!=0&& change.getChangeId()!=0){
            try {
                if(Actions.insert("change",change)){
                    data=new ResponseBase();
                }else {
                    data=ResponseBase.failBuild(msgCode.DatabaseError);
                }

            }catch (Exception e){
                e.printStackTrace();
                data = ResponseBase.failBuild(msgCode.MSG_CODE);
            }
        }else {

            data = ResponseBase.failBuild(msgCode.NO_Args);
        }
        return ResponseUtils.toJson(data);
    }

    @PostMapping("/insert/punishment")
    public String insert(@ModelAttribute Punishment punishment){
        ResponseBase data=null;
        if(punishment.getStudentId()!=0&& punishment.getLevels()!=0){
            try {
                if(Actions.insert("punishment",punishment)){
                    data=new ResponseBase();
                }else {
                    data=ResponseBase.failBuild(msgCode.DatabaseError);
                }

            }catch (Exception e){
                e.printStackTrace();
                data = ResponseBase.failBuild(msgCode.MSG_CODE);
            }
        }else {

            data = ResponseBase.failBuild(msgCode.NO_Args);
        }
        return ResponseUtils.toJson(data);
    }
    @PostMapping("/insert/reward")
    public String insert(@ModelAttribute Reward reward){
        ResponseBase data=null;
        if(reward.getStudentId()!=0&& reward.getLevels()!=0){
            try {
                if(Actions.insert("reward",reward)){
                    data=new ResponseBase();
                }else {
                    data=ResponseBase.failBuild(msgCode.DatabaseError);
                }

            }catch (Exception e){
                e.printStackTrace();
                data = ResponseBase.failBuild(msgCode.MSG_CODE);
            }
        }else {

            data = ResponseBase.failBuild(msgCode.NO_Args);
        }
        return ResponseUtils.toJson(data);
    }



}
