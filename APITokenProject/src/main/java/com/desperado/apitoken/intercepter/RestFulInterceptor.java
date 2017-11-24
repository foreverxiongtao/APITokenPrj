package com.desperado.apitoken.intercepter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.desperado.apitoken.dao.TokenDao;
import com.desperado.apitoken.domain.RestFulBean;
import com.desperado.apitoken.domain.TokenBean;
import com.desperado.apitoken.util.RestFulUtil;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/*
 *
 *
 * 版 权 :@Copyright 中海互联版权所有
 *
 * 作 者 :xt
 *
 * 版 本 :v1.0.0
 *
 * 创建日期 :2017/11/23  15:17
 *
 * 描 述 :token拦截器
 *
 * 修订日期 :
 */
public class RestFulInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenDao tokenDao;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        // TODO Auto-generated method stub

        String uri = request.getRequestURI();
        Map<String, String> headerMaps = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            headerMaps.put(key, value);
        }
        if (!uri.endsWith(".do")) {
            return true;
        }
        if (uri.endsWith("user/login.do"))//登录
        {
            return true;

        } else if (uri.endsWith("user/register.do"))//注册
        {
            return true;
        } else if (uri.endsWith("pay/verifyalipayresult.do"))//支付验证(暂留)
        {
            return true;
        } else {
            TokenBean tokenBean = tokenDao.isTokenAvailable(headerMaps.get("phone"));
            if (tokenBean != null && tokenBean.getToken().equals(headerMaps.get("token"))) {
                Date currentDate = new Date();
                if (currentDate.after(tokenBean.getExpireTime())) { /***token过期了**/
                    RestFulUtil.getInstance().showValidateErrorMsg(response, "用户信息已过期，请从新登录");
                    return false;
                } else {
                    return true;
                }
            } else {
                RestFulUtil.getInstance().showValidateErrorMsg(response, "用户身份验证失败");
                return false;
            }
        }
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
    }

}
