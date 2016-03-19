package com.yslm.web.controller.login;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yslm.common.HTTPCodeStatus;
import com.yslm.model.user.User;
import com.yslm.service.user.UserServices;
import com.yslm.util.request.RequestUtil;
import com.yslm.web.entity.ResponseEntity;
import com.yslm.web.security.util.SecurityUtil;

@Controller
public class LoginController {
    
    @Resource(name = "userService")
    private UserServices userService;

    
    @RequestMapping(value="loginSuccess")
    @ResponseBody
    @SuppressWarnings("rawtypes")
    public ResponseEntity loginSuccess(HttpServletRequest request) {
        User user = SecurityUtil.currentLogin();
        //System.out.println(user.getRealName()+"登录成功");
        user = userService.getById(user.getId());
        user.setLastestLoginTime(new Date());
        user.setLastestLoginIp(RequestUtil.getRemoteIPAddress(request));
        userService.update(user);
        return new ResponseEntity(HTTPCodeStatus.HTTPCODE_OK, HTTPCodeStatus.HTTPCODE_OK_MESSAGE);
    }

    @RequestMapping(value="loginFailed")
    public String loginFailed() {
        System.out.println("登录失败");
        return "redirect:logout"; 
    }
}
