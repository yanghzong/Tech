package com.wd.tech.model;

import com.google.gson.Gson;
import com.wd.tech.contractclass.ContractClass;
import com.wd.tech.icallback.ICallBack;
import com.wd.tech.utils.datarequestutil.RxRetrofitUtils;

import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Author : 张自力
 * Created on time.
 *
 * 数据接口地址请求 Model层
 *
 *    实现契约类里的  M层的接口
 *                并实现其中的方法
 */

public class Model implements ContractClass.IM {

    /**
     *  1.普通的Get 方式
     *
     *  @param url 为 除了网络环境外的后半段地址
     *
     * */
    @Override
    public void doGetM(String url, final ICallBack iCallBack, final Type type) {
        //通过工具类对象 调用方法
        RxRetrofitUtils.getInstance().doGetData(url, new RxRetrofitUtils.RxRetrofitListener() {
            @Override
            public void onSuccessU(String data) {
                //成功返回数据 使用Gson进行解析
                Object o = new Gson().fromJson(data, type);
                //判断对象是否为空
                if(iCallBack!=null){
                    iCallBack.onSuccessIC(o);
                }
            }

            @Override
            public void onFailed(String message) {
                //数据返回失败
                //判断对象是否为空
                if(iCallBack!=null){
                    iCallBack.onFailed(message);
                }
            }
        });
    }


    /**
     * 2.post 以body方式进行提交
     *
     * @param requestBody  请求体
     * @param url 为 除了网络环境外的后半段地址
     *
     * */
    @Override
    public void doPostM(String url, RequestBody requestBody, final ICallBack iCallBack, final Type type) {
        //通过工具类对象 调用方法
        RxRetrofitUtils.getInstance().doPostData(url,requestBody,new RxRetrofitUtils.RxRetrofitListener() {
            @Override
            public void onSuccessU(String data) {
                //成功返回数据 使用Gson进行解析
                Object o = new Gson().fromJson(data, type);
                //判断对象是否为空
                if(iCallBack!=null){
                    iCallBack.onSuccessIC(o);
                }
            }

            @Override
            public void onFailed(String message) {
                //数据返回失败
                //判断对象是否为空
                if(iCallBack!=null){
                    iCallBack.onFailed(message);
                }
            }
        });
    }

    /**
     * 3.post 以MultipartBody方式进行提交
     *   应用场景  上传头像
     *
     * @param part  multipartBody体
     * @param url 为 除了网络环境外的后半段地址
     *
     * */
    @Override
    public void doPostUploadHeadPicFileM(MultipartBody.Part part, String url, final ICallBack iCallBack, final Type type) {
        //通过工具类对象 调用方法
        RxRetrofitUtils.getInstance().doPostUploadHeadPicFileData(part,url,new RxRetrofitUtils.RxRetrofitListener() {
            @Override
            public void onSuccessU(String data) {
                //成功返回数据 使用Gson进行解析
                Object o = new Gson().fromJson(data, type);
                //判断对象是否为空
                if(iCallBack!=null){
                    iCallBack.onSuccessIC(o);
                }
            }

            @Override
            public void onFailed(String message) {
                //数据返回失败
                //判断对象是否为空
                if(iCallBack!=null){
                    iCallBack.onFailed(message);
                }
            }
        });
    }

    /**
     * 4.post 以map方式进行提交
     *
     * @param map<String,String>  map集合
     * @param url 为 除了网络环境外的后半段地址
     * */
    @Override
    public void doPostMapM(String url, Map<String,String> map, final ICallBack iCallBack, final Type type) {
        //通过工具类对象 调用方法
        RxRetrofitUtils.getInstance().doPostMapData(url,map,new RxRetrofitUtils.RxRetrofitListener() {
            @Override
            public void onSuccessU(String data) {
                //成功返回数据 使用Gson进行解析
                Object o = new Gson().fromJson(data, type);
                //判断对象是否为空
                if(iCallBack!=null){
                    iCallBack.onSuccessIC(o);
                }
            }

            @Override
            public void onFailed(String message) {
                //数据返回失败
                //判断对象是否为空
                if(iCallBack!=null){
                    iCallBack.onFailed(message);
                }
            }
        });
    }

}
