package com.yyjccc.studentsystem.Utils;

import com.yyjccc.studentsystem.Utils.Response.Message;
import com.yyjccc.studentsystem.Utils.Response.msgCode;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ResponseBase<T> {
    private Integer code=msgCode.Sucess.getCode();
    private String msg= msgCode.Sucess.getMsg();
    private Message<T> info;
    private Boolean ok=true;

    public void setCodeMsg(msgCode stats){
        this.code=stats.getCode();
        this.msg=stats.getMsg();
    }
    public static ResponseBase failBuild(msgCode stats){
        ResponseBase responseBase=new ResponseBase();
        responseBase.setCodeMsg(stats);
        responseBase.setOk(false);
        return responseBase;
    }
}
