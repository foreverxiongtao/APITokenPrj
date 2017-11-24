package com.desperado.apitoken.dao;


import com.desperado.apitoken.domain.UserBean;
import org.springframework.stereotype.Repository;

/**
 * Created by ywl5320 on 2017/10/12.
 */
@Repository
public interface UserDao {

    /**
     * 注册
     *
     * @param userBean
     */
    int registor(UserBean userBean);

    /**
     * 登陆
     *
     * @return
     */
    UserBean login(UserBean userBean);

    /**
     * 根据名字获取用户信息
     *
     * @param phone
     * @return
     */
    UserBean getUser(UserBean userBean);

}
