package com.wd.tech.contractclass;

import com.wd.tech.icallback.ICallBack;

import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Author : 张自力
 * Created on time.
 *
 * 契约类
 *
 */

public interface ContractClass {

    /**
     * 一. 创建M层接口
     *
     */
    interface IM{

        /**
         *  1.普通的Get 方式
         *
         *  @param url 为 除了网络环境外的后半段地址
         *
         * */
        void doGetM(String url, ICallBack iCallBack, Type type);

        /**
         * 2.post 以body方式进行提交
         *
         * @param requestBody  请求体
         * @param url 为 除了网络环境外的后半段地址
         *
         * */
        void doPostM(String url, RequestBody requestBody, ICallBack iCallBack, Type type);

        /**
         * 3.post 以MultipartBody方式进行提交
         *   应用场景  上传头像
         *
         * @param part  multipartBody体
         * @param url 为 除了网络环境外的后半段地址
         *
         * */
        void doPostUploadHeadPicFileM(MultipartBody.Part part, String url, ICallBack iCallBack, Type type);

        /**
         * 4.post 以map方式进行提交
         *
         * @param map<String,String>  map集合
         * @param url 为 除了网络环境外的后半段地址
         * */
        void doPostMapM(String url, Map<String, String> map, ICallBack iCallBack, Type type);

        /**
         * 5.post 以map方式进行提交
         *
         * @param map<String,String>  map集合
         * @param url 为 除了网络环境外的后半段地址
         * */
        void doGetNetSomeThingM(String url, Map<String, String> map,String userId,String sessionId, ICallBack iCallBack, Type type);

    }


    /**
     * 二. 创建P层接口
     *
     */
    interface IP{

        /**
         *  1.普通的Get 方式
         *
         *  @param url 为 除了网络环境外的后半段地址
         *
         * */
        void doGetP(String url, Type type);

        /**
         * 2.post 以body方式进行提交
         *
         * @param requestBody  请求体
         * @param url 为 除了网络环境外的后半段地址
         *
         * */
        void doPostP(String url, RequestBody requestBody, Type type);

        /**
         * 3.post 以MultipartBody方式进行提交
         *   应用场景  上传头像
         *
         * @param part  multipartBody体
         * @param url 为 除了网络环境外的后半段地址
         *
         * */
        void doPostUploadHeadPicFileP(int userId, String sessionId, MultipartBody.Part part, String url, Type type);

        /**
         * 4.post 以map方式进行提交
         *
         * @param map<String,String>  map集合
         * @param url 为 除了网络环境外的后半段地址
         * */
        void doPostMapP(String url, Map<String, String> map, Type type);

        /**
         * 4.post 以map方式进行提交
         *
         * @param map<String,String>  map集合
         * @param url 为 除了网络环境外的后半段地址
         * */
        void doGetNetSomeThingP(String url, Map<String, String> map, String userId,String sessionId, Type type);

    }


}
