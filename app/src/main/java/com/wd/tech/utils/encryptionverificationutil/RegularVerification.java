package com.wd.tech.utils.encryptionverificationutil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author : 张自力
 * Created on time.
 *
 * 正则验证 工具
 *
 */

public class RegularVerification {

    /**
     * 验证手机号合法性 匹配正整数
     * @param Phone 手机号
     *
     *    通过返回值为true为合法  和  false不合法
     * */
    public static boolean JudgePhone(String Phone){
        Pattern pphone = Pattern
                .compile("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$");
        Matcher loginphone = pphone.matcher(Phone);
        boolean ismatchesphone = loginphone.matches();
        return ismatchesphone;
    }

    /**
     * 验证密码 合法性
     *   匹配: 必须字母开头 5-17位
     *   以字母开头，长度在6~18之间，只能包含字母、数字和下划线！
     * @param Password 密码
     *    通过返回值为true为合法  和  false不合法
     *
     * */
    public static boolean JudgePassword(String Password){
        Pattern ppwd = Pattern
                .compile("^[a-zA-Z]\\w{5,17}$");
        Matcher loginpwd = ppwd.matcher(Password);
        boolean ismatchespwd = loginpwd.matches();
        return ismatchespwd;
    }

}
