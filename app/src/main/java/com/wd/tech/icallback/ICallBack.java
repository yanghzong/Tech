package com.wd.tech.icallback;

/**
 * Author : 张自力
 * Created on time.
 *
 * 接口回调
 *
 */

public interface ICallBack {

    /**
     * 成功的回调方法
     * @param o 成功的数据回调
     *
     * */
    void onSuccessIC(Object o);

    /**
     * 成功的回调方法
     * @param message 失败的信息回调
     *
     * */
    void onFailed(String message);


}
