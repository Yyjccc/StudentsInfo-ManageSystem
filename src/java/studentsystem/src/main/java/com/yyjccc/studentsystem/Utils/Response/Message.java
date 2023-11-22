package com.yyjccc.studentsystem.Utils.Response;

import lombok.Data;

@Data
public class Message<T>  {
    private T data;
    private  String token;
   public Message(String token){
       this.token=token;
   }

   public Message(int mode,T data){
       this.data=data;
   }



}
