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

    //4 社区列表
    public static final String APICommunityListUrl="techApi/community/v1/findCommunityList";

}
