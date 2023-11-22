package com.yyjccc.studentsystem.Service;

import Mode.Mapper.ActionMapper;


import Mode.enity.Action.Change;
import Mode.enity.Action.Punishment;
import Mode.enity.Action.Reward;
import com.yyjccc.studentsystem.Utils.Datautils;
import com.yyjccc.studentsystem.Utils.Response.Message;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;



public class Actions {



    public static List getAll(String ActionType) throws IOException {

        SqlSession sqlSession = Datautils.SqlStart();
        ActionMapper mapper= sqlSession.getMapper(ActionMapper.class);
        List result=null;
        if (ActionType.equals("change")){
            result =mapper.changeGetAll();

        } else if (ActionType.equals("reward")) {
           result=mapper.rewardGetAll();
        }else if(ActionType.equals("punishment")){
           result=mapper.punishGetAll();
        }
       sqlSession.close();
       return result;
    }

    public static List<Object> query(String ActionType,String type,String value) throws IOException {
       List result = null;
        SqlSession sqlSession = Datautils.SqlStart();
        ActionMapper mapper= sqlSession.getMapper(ActionMapper.class);
        if (ActionType.equals("change")){
            result =mapper.changequery(type, value);
        } else if (ActionType.equals("reward")) {
            result=mapper.rewardquery(type,value);
        }else if(ActionType.equals("punishment")){
            result=mapper.punishquery(type,value);
        }
        sqlSession.close();
        return result;
    }


    public  static Boolean insert(String ActionType,Object o) throws IOException {
       boolean result=false;
        SqlSession sqlSession = Datautils.SqlStart();
        ActionMapper mapper= sqlSession.getMapper(ActionMapper.class);
        if (ActionType.equals("change")){
            Change change=(Change) o;
            try {
                result =mapper.changeinsert(change);
            }catch (Exception e){
                e.printStackTrace();
            }

        } else if (ActionType.equals("reward")) {
            Reward reward=(Reward) o;
            result=mapper.rewardinsert(reward);
        }else if(ActionType.equals("punishment")){
            Punishment punishment=(Punishment) o;
            result=mapper.punishinsert(punishment);
        }
        sqlSession.close();
        return result;
    }


    public static Boolean update(String ActionType,int id,String type,String value) throws IOException {
       boolean result=false;
        SqlSession sqlSession = Datautils.SqlStart();
        ActionMapper mapper= sqlSession.getMapper(ActionMapper.class);
       result=mapper.update(ActionType,id,type,value);
        sqlSession.close();
       return result;

    }

    public static Boolean delete(String ActionType,int id) throws IOException {
        SqlSession sqlSession = Datautils.SqlStart();
        ActionMapper mapper= sqlSession.getMapper(ActionMapper.class);
       boolean result=mapper.delete(ActionType,id);
       sqlSession.close();
       return result;
    }

}
