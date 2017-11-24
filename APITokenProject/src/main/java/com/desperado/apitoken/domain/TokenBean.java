package com.desperado.apitoken.domain;

import java.util.Date;

/*
 *
 *
 * 版 权 :@Copyright 中海互联版权所有
 *
 * 作 者 :xt
 *
 * 版 本 :v1.0.0
 *
 * 创建日期 :2017/11/23  12:15
 *
 * 描 述 :token实体类
 *
 * 修订日期 :
 */
public class TokenBean {
    private Integer id;
    private String phone;     //电话号码
    private String token;    //token
    private Date expireTime; //过期时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
