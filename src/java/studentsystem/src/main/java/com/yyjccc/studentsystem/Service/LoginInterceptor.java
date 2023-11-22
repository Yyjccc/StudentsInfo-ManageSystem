package com.yyjccc.studentsystem.Service;

import com.yyjccc.studentsystem.Utils.Response.msgCode;
import com.yyjccc.studentsystem.Utils.ResponseBase;
import com.yyjccc.studentsystem.Utils.ResponseUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        // 从请求头中获取 token
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            // 遍历 Cookie 数组，找到名为 "jwt" 的 Cookie
//            for (Cookie cookie : cookies) {
//                if ("jwt".equals(cookie.getName())) {
//                    // 找到了名为 "jwt" 的 Cookie，获取其值
//                    String jwtValue = cookie.getValue();
//                    if (User.checkToken(jwtValue)) {
//                        return true;  // 校验通过，继续处理请求
//                    } else {
//                        // 校验失败，返回错误响应
//                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                    }
//                }
//            }
//        }
//
//        ResponseUtils.ResAppJson(response,ResponseBase.failBuild(msgCode.NoLogin));
//            return false;
        return true;

        // 在实际项目中，你可能需要解析 token 并验证其有效性
        // 这里只是简单演示，实际中你可能需要使用 JWT 库进行解析和验证

    }

}
