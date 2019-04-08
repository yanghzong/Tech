package com.wd.tech.iview;

/**
 * Author : 张自力
 * Created on time.
 *
 * 接口
 *
 */

public interface IView <T>{

    /**
     * 成功的回调方法
     * @param T 成功数据
     *
     * */
    void onSuccessIV(T T);

    /**
     * 成功的回调方法
     * @param message 失败信息
     *
     * */
    void onFailed(String message);

}
