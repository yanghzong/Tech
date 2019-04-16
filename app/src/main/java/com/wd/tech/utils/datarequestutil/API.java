package com.wd.tech.utils.datarequestutil;


/**
 * Author : 张自力
 * Created on time.
 *
 * 网址存储API
 *
 */

public class API {

    //1.1 内网环境
    public static final String APIBaseIntranetUrl="https://172.17.8.100/";
    //1.2 外网环境
    public static final String APIBaseOuternetUrl="https://mobile.bwstudent.com/";

    //2 注册
    public static final String APIUserRegisterUrl="techApi/user/v1/register";

    //3 登录
    public static final String APIUserLoginUrl="techApi/user/v1/login";
    //微信登录
    public static final String APIWXLoginUrl="techApi/user/v1/weChatLogin";

    //根据手机号查询用户信息
    public static final String FindUserByPhoneUrl="techApi/user/verify/v1/findUserByPhone";

}
