package com.yyjccc.studentsystem.Controller;


import com.yyjccc.studentsystem.Service.User;
import com.yyjccc.studentsystem.Utils.Response.Message;
import com.yyjccc.studentsystem.Utils.Response.msgCode;
import com.yyjccc.studentsystem.Utils.ResponseBase;
import com.yyjccc.studentsystem.Utils.ResponseUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
public class LoginController {
    @PostMapping("/api/login")
    public String Login(@RequestParam String username, @RequestParam String password) throws IOException {
        ResponseBase data=new ResponseBase();
        User user=new User(username,password);
        if(user.login()){
            String token=User.createToken(user.getLoginUser());
            data.setCodeMsg(msgCode.LoginSucess);
            data.setInfo(new Message(token));
        }else {
            data.setCodeMsg(msgCode.ErrorLogin);
            data.setOk(false);
        }
        return ResponseUtils.toJson(data);
    }
    public void register(){}

    @GetMapping("/api/test")
    public String test(){
        return "test";
    }

}
