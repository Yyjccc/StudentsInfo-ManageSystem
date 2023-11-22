package com.yyjccc.studentsystem.Utils;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtils {
    public static void ResAppJson(HttpServletResponse response,Object object){
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try(PrintWriter pw=response.getWriter()) {
        pw.write(JSON.toJSONString(object));
        }catch (IOException e){
            System.out.println("输出异常");
        }
    }

    public static String toJson(Object o){
        return JSON.toJSONString(o);
    }
}
