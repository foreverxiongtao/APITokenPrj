package com.desperado.apitoken.service;

import com.desperado.apitoken.dao.TokenDao;
import com.desperado.apitoken.dao.UserDao;
import com.desperado.apitoken.domain.RestFulBean;
import com.desperado.apitoken.domain.UserBean;
import com.desperado.apitoken.domain.TokenBean;
import com.desperado.apitoken.util.DateUtils;
import com.desperado.apitoken.util.MD5;
import com.desperado.apitoken.util.RestFulUtil;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenDao tokenDao;


    @Transactional(readOnly = false)
    public RestFulBean<UserBean> registorServer(UserBean userBean) {
        if (!StringUtils.isEmptyOrWhitespaceOnly(userBean.getPhone())) {
            UserBean user = userDao.getUser(userBean);
            if (user != null) {
                return RestFulUtil.getInstance().getResuFulBean(null, 1, "已经注册过了");
            } else {
                int row = userDao.registor(userBean);
                if (row > 0) {
                    saveOrUpdateToken(userBean);
                    return RestFulUtil.getInstance().getResuFulBean(userBean, 0, "注册成功");
                } else {
                    return RestFulUtil.getInstance().getResuFulBean(null, 1, "注册失败");
                }
            }
        } else {
            return RestFulUtil.getInstance().getResuFulBean(null, 1, "手机号码不能为空");
        }
    }

    public RestFulBean<UserBean> login(UserBean userBean) {
        if (!StringUtils.isEmptyOrWhitespaceOnly(userBean.getPhone()) && !StringUtils.isEmptyOrWhitespaceOnly(userBean.getPassword())) {
            UserBean user = userDao.login(userBean);
            if (user != null) {
                saveOrUpdateToken(user);
                return RestFulUtil.getInstance().getResuFulBean(user, 0, "登录成功");
            } else {
                return RestFulUtil.getInstance().getResuFulBean(null, 1, "用户名密码错误");
            }
        } else {
            return RestFulUtil.getInstance().getResuFulBean(null, 1, "用户不存在");
        }
    }

    public RestFulBean<UserBean> userinfo(UserBean us) {
        UserBean userBean = userDao.getUser(us);
        if (userBean != null) {
            return RestFulUtil.getInstance().getResuFulBean(userBean, 0, "获取成功");
        }
        return RestFulUtil.getInstance().getResuFulBean(null, 1, "用户不存在");
    }

    private void saveOrUpdateToken(UserBean userBean) {
        String token = null;
        try {
            token = MD5.encryptMD5(String.valueOf(System.currentTimeMillis()) + "appservice.02154778ke783dad34");
        } catch (Exception e) {
            e.printStackTrace();
        }
        userBean.setToken(token);
        TokenBean tokenBean = tokenDao.isTokenAvailable(userBean.getPhone());
        if (tokenBean != null) {
            tokenBean.setToken(token);
        } else {
            tokenBean = new TokenBean();
            tokenBean.setPhone(userBean.getPhone());
            tokenBean.setToken(userBean.getToken());
        }
        tokenBean.setExpireTime(DateUtils.getTokenExpriDate());
        tokenDao.saveOrUpdageToken(tokenBean);
    }

}
