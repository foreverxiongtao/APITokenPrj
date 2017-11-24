package com.desperado.apitoken.dao;

import com.desperado.apitoken.domain.TokenBean;
import org.springframework.stereotype.Repository;

/**
 * Created by win 10 on 2017/11/23.
 */
@Repository
public interface TokenDao {

    /**
     * 登录或注册时写入token
     *
     * @param tokenBean
     */
    void saveOrUpdageToken(TokenBean tokenBean);

    /**
     * 根据电话号码获取token
     *
     * @param phone
     * @return
     */
    TokenBean isTokenAvailable(String phone);
}
