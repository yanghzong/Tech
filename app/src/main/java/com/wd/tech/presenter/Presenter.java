package com.wd.tech.presenter;

import com.wd.tech.contractclass.ContractClass;
import com.wd.tech.icallback.ICallBack;
import com.wd.tech.iview.IView;
import com.wd.tech.model.Model;

import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Author : 张自力
 * Created on time.
 *
 * 链接Model  和  View 层的桥梁  Presenter层
 *
 *   实现契约类 的 P层接口  并实现方法
 *
 */

public class Presenter implements ContractClass.IP {

    //创建IView 和  Model对象
    private IView miview;
    private Model mmodel;

    /**
     * 0.1 . 关联方法
     *
     * 填写mcontent即可
     *
     * */
    public void attach(IView iview) {
        miview = iview;
        mmodel = new Model();
    }

    /**
     *  1.普通的Get 方式
     *
     *  @param url 为 除了网络环境外的后半段地址
     *  @param type 泛型  封装类
     *
     * */
    @Override
    public void doGetP(String url,Type type) {
        //通过Model对象  调用方法
        mmodel.doGetM(url, new ICallBack() {
            @Override
            public void onSuccessIC(Object o) {
                if(o!=null){
                    if(miview!=null){
                        //接口回调
                        miview.onSuccessIV(o);
                    }
                }
            }

            @Override
            public void onFailed(String message) {
                if(miview!=null){
                    //接口回调
                    miview.onFailed(message);
                }
            }
        },type);
    }


    /**
     * 2.post 以body方式进行提交
     *
     * @param requestBody  请求体
     * @param url 为 除了网络环境外的后半段地址
     * @param type 泛型  封装类
     *
     * */
    @Override
    public void doPostP(String url, RequestBody requestBody, Type type) {
        //通过Model对象  调用方法dopost方法
        //通过Model对象  调用方法
        mmodel.doPostM(url,requestBody ,new ICallBack() {
            @Override
            public void onSuccessIC(Object o) {
               if(o!=null){
                  if(miview!=null){
                      miview.onSuccessIV(o);
                  }
               }
            }

            @Override
            public void onFailed(String message) {
                if(miview!=null){
                    miview.onFailed(message);
                }
            }
        },type);
    }

    /**
     * 3.post 以MultipartBody方式进行提交
     *   应用场景  上传头像
     *
     * @param part  multipartBody体
     * @param url 为 除了网络环境外的后半段地址
     * @param type 泛型  封装类
     *
     * */
    @Override
    public void doPostUploadHeadPicFileP(int userId, String sessionId, MultipartBody.Part part, String url, Type type) {
        //通过Model对象  调用方法
        mmodel.doPostUploadHeadPicFileM(part,url,new ICallBack() {
            @Override
            public void onSuccessIC(Object o) {
                if(o!=null){
                    if(miview!=null){
                        miview.onSuccessIV(o);
                    }
                }
            }

            @Override
            public void onFailed(String message) {
                if(miview!=null){
                    miview.onFailed(message);
                }
            }
        },type);
    }

    /**
     * 4.post 以map方式进行提交
     *
     * @param map<String,String>  map集合
     * @param url 为 除了网络环境外的后半段地址
     * @param type 泛型  封装类
     * */
    @Override
    public void doPostMapP(String url, Map<String,String> map, Type type) {
        //通过Model对象  调用方法dopost方法
        //通过Model对象  调用方法
        mmodel.doPostMapM(url,map ,new ICallBack() {
            @Override
            public void onSuccessIC(Object o) {
                if(o!=null){
                    if(miview!=null){
                        miview.onSuccessIV(o);
                    }
                }
            }

            @Override
            public void onFailed(String message) {
                if(miview!=null){
                    miview.onFailed(message);
                }
            }
        },type);
    }

    @Override
    public void doGetNetSomeThingP(String url, Map<String, String> map, String userId, String sessionId, Type type) {
        //通过Model对象  调用方法dopost方法
        //通过Model对象  调用方法
        mmodel.doGetNetSomeThingM(url,map , userId,sessionId,new ICallBack() {
            @Override
            public void onSuccessIC(Object o) {
                if(o!=null){
                    if(miview!=null){
                        miview.onSuccessIV(o);
                    }
                }
            }

            @Override
            public void onFailed(String message) {
                if(miview!=null){
                    miview.onFailed(message);
                }
            }
        },type);
    }

    /**
     * 0.1.解除关联
     *
     *  防止内存泄漏
     * */
    public void datach(){
        //0.2.1 置空view
        if(miview!=null){
            miview=null;
        }

        //0.2.2 置空model
        if(mmodel!=null){
            mmodel=null;
        }
    }


}
