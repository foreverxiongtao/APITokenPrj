package com.desperado.apitoken.controller;

import com.desperado.apitoken.domain.RestFulBean;
import com.desperado.apitoken.domain.UserBean;
import com.desperado.apitoken.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by win 10 on 2017/11/23.
 */

@Controller
@RequestMapping("user")
public class UserController {


    @Autowired
    UserService userService;

    /**
     * 详情
     *
     * @param phone
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/userinfo.do")
    public RestFulBean<UserBean> userInfo(@RequestHeader(value = "phone") String phone, HttpServletRequest request, HttpServletResponse response, Model model, UserBean userBean) {
        System.out.println("phone:");
        userBean.setPhone(phone);
        return userService.userinfo(userBean);
    }

    /**
     * 注册
     *
     * @param userBean
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register.do")
    public RestFulBean<UserBean> registorServer(HttpServletRequest request, HttpServletResponse response, Model model, UserBean userBean) {
        if (userBean != null) {
            System.out.println("phone:" + userBean.getPhone());
        }
        return userService.registorServer(userBean);
    }


    /**
     * 登录
     *
     * @param userBean
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login.do")
    public RestFulBean<UserBean> login(HttpServletRequest request, HttpServletResponse response, UserBean userBean) {
        if (userBean != null) {
            System.out.println("phone:" + userBean.getPhone());
        }
        return userService.login(userBean);
    }
}
