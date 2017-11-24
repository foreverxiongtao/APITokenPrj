package com.desperado.apitoken.util;

import java.util.Date;
import java.util.Properties;

/**
 * Created by win 10 on 2017/11/23.
 */
public class DateUtils {


    /**
     * 获取过期时间
     * @return
     */
    public static Date getTokenExpriDate() {
        Date currentDate = new Date();
        PropertiesLoader propertiesLoader = new PropertiesLoader("main.properties");
        //获取过期时间
        int expire_time = propertiesLoader.getInteger("token.expire_time");
        Date target = new Date(currentDate.getTime() + expire_time);
        return target;
    }
}
