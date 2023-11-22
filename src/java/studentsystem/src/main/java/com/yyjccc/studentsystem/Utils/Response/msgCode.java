package com.yyjccc.studentsystem.Utils.Response;




public enum msgCode {
    Sucess(2000,"操作成功"),
    LoginSucess(2001,"登录成功"),
    ErrorLogin(2001,"不存在该用户或者密码错误"),
    NoLogin(2002,"请先登录"),
    MSG_CODE(3000,"参数错误"),

    NO_Args(3001,"缺少必要参数"),
    No_data(3002,"不存在该数据"),
    Vaule_Error(3003,"参数取值错误"),

    DatabaseError(4000,"数据库错误"),

    ;


    msgCode(Integer code ,String description){
        this.code=code;
        this.msg=description;
    }
    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
