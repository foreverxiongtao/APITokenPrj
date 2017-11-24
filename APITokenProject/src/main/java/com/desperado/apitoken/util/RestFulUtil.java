package com.desperado.apitoken.util;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.desperado.apitoken.domain.RestFulBean;
import com.desperado.apitoken.domain.TokenBean;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by ywl5320 on 2017-10-15.
 */
public class RestFulUtil<T> {

    private RestFulUtil() {
    }

    public static RestFulUtil getInstance() {
        return new RestFulUtil();
    }

    public RestFulBean<T> getResuFulBean(T o, int status, String msg) {
        RestFulBean<T> objectRestFulBean = new RestFulBean<T>();
        objectRestFulBean.setStatus(status);
        objectRestFulBean.setMsg(msg);
        objectRestFulBean.setData(o);
        return objectRestFulBean;
    }


    public void showValidateErrorMsg(HttpServletResponse response,String msg) throws IOException {
        RestFulBean<TokenBean> resuFulBean = RestFulUtil.getInstance().getResuFulBean(null, 1,msg );
        //这句话的意思，是让浏览器用utf8来解析返回的数据
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        //这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859
        response.setCharacterEncoding("UTF-8");
        Writer writer = response.getWriter();
        writer.write(JSONObject.toJSONString(resuFulBean, SerializerFeature.WriteMapNullValue));
    }

}
