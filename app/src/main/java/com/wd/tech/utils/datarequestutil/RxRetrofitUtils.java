package com.wd.tech.utils.datarequestutil;

import android.text.TextUtils;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.wd.tech.application.MyApplication;
import com.wd.tech.utils.networkutil.MyInterceptor;
import com.wd.tech.utils.networkutil.NetUtil;
import com.wd.tech.utils.encryptionverificationutil.SSLSocketFactoryCompat;
import com.wd.tech.utils.storageutil.SPUtil;

import java.io.File;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author : 张自力
 * Created on time.
 *
 * 网络请求工具类
 */

public class RxRetrofitUtils {

    /**
     * 单例模式 对象
     *
     * */
    private static volatile RxRetrofitUtils instance;
    private final Retrofit.Builder rbuilder;

    //缓存声明
    File httpCacheDirectory = new File("/sdcard", "cache_xx");
    Cache cache  = new Cache(httpCacheDirectory, 10240 * 1024 * 10); //10M
    private final SSLSocketFactory sslSocketFactory;
    private final X509TrustManager trustAllCert;

    /**
     * 单例模式 构造方法
     *
     * */
    private RxRetrofitUtils() {
        //创建日志拦截器
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("http日志拦截器打印:", "log: " + message);
            }
        });
        //添加拦截器级别
        httpLoggingInterceptor.setLevel(level);

        //通过SP得到存储的sessionid 和 userid
        /*int userId = (int) SPUtil.getInt(MyApplication.applicationContext, "userId", 0);
        String sessionId = (String) SPUtil.getString(MyApplication.applicationContext, "sessionId", "");

         Map<String,String> map = new HashMap<>();
        map.put("sessionId",sessionId);
        map.put("userId",userId+"");*/

        //使用https
        try {
            // 自定义一个信任所有证书的TrustManager，添加SSLSocketFactory的时候要用到
            trustAllCert = new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                }
                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                }
                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }
            };
            sslSocketFactory = new SSLSocketFactoryCompat(trustAllCert);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Okhttpclient对象的创建
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .sslSocketFactory(sslSocketFactory,trustAllCert)
                //.addInterceptor(new MyInterceptor(map))//拦截器添加请求头
                .addInterceptor(httpLoggingInterceptor)//添加日志拦截器
                .addNetworkInterceptor(new Interceptor() {//添加缓存
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Log.e("MainActivity", "新请求 =request==" + request.toString());

                        Response response = chain.proceed(request);
                        Response response_build ;
                        if (NetUtil.checkNet(MyApplication.applicationContext)) {
                            int maxAge = 60 * 60*24; // 有网络的时候从缓存1天后失效
                            response_build = response.newBuilder()
                                    .removeHeader("Pragma")
                                    .removeHeader("Cache-Control")
                                    .header("Cache-Control", "public, max-age=" + maxAge)
                                    .build();
                        } else {
                            int maxStale = 60 * 60 * 24 * 28; // // 无网络缓存保存四周
                            response_build = response.newBuilder()
                                    .removeHeader("Pragma")
                                    .removeHeader("Cache-Control")
                                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                    .build();
                        }

                        return response_build;
                    }
                })
                .cache(cache)
                .build();



        //创建Retrofit对象  添加日志拦截器
        rbuilder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient);

    }

    /**
     * 单例模式 双重锁机制
     *
     * */
    public static RxRetrofitUtils getInstance(){
        if(instance==null){
            synchronized (RxRetrofitUtils.class){
                if(instance==null){
                    instance = new RxRetrofitUtils();
                }
            }
        }

        return instance;
    }

    /**
     * 1.普通的doget方法
     *
     *  @param url 除环境外地址
     *  @param rxRetrofitListener  接口回调对象
     *
     * */
    public RxRetrofitUtils doGetData(String url,RxRetrofitListener rxRetrofitListener){
        //通过方法  得到RetrofitIView对象
        RetrofitIView retrofitIView = getRetrofitIViewMethod();
        //通过对象调用方法
        /*
         *  防止暴力点击
         *  throttleFirst(2,TimeUnit.SECONDS)
         * 1. 此处采用了RxBinding：RxView.clicks(button) = 对控件点击进行监听，需要引入依赖：compile 'com.jakewharton.rxbinding2:rxbinding:2.0.0'
         * 2. 传入Button控件，点击时，都会发送数据事件（但由于使用了throttleFirst（）操作符，
         *    所以只会发送该段时间内的第1次点击事件）
         **/
        retrofitIView.doGet(url)
                .throttleFirst(2,TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserverMethod(rxRetrofitListener));
        return instance;
    }


    /**
     * 2.post 以body方式进行提交
     *
     * @param url 为 除了网络环境外的后半段地址
     * @param requestBody  请求体
     * @param rxRetrofitListener   接口回调对象
     *
     * */
    public RxRetrofitUtils doPostData(String url, RequestBody requestBody, RxRetrofitListener rxRetrofitListener){
        //通过方法  得到RetrofitIView对象
        RetrofitIView retrofitIView = getRetrofitIViewMethod();
        //通过对象调用方法
        retrofitIView.getPostBody(requestBody,url)
                .throttleFirst(2,TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserverMethod(rxRetrofitListener));
        return instance;
    }

    /**
     * 3.post 以MultipartBody方式进行提交
     *   应用场景  上传头像
     *
     * @param part  multipartBody体
     * @param url 为 除了网络环境外的后半段地址
     *
     * */
    public RxRetrofitUtils doPostUploadHeadPicFileData(MultipartBody.Part part,String url,RxRetrofitListener rxRetrofitListener){
        //通过方法  得到RetrofitIView对象
        RetrofitIView retrofitIView = getRetrofitIViewMethod();
        //通过对象调用方法
        retrofitIView.getPostUploadHeadPicFile(part,url)
                .throttleFirst(2,TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserverMethod(rxRetrofitListener));
        return instance;
    }

    /**
     * 4.post 以map方式进行提交
     *
     * @param map<String,String>  map集合
     * @param url 为 除了网络环境外的后半段地址
     * */
    public RxRetrofitUtils doPostMapData(String url,Map<String,String> map, RxRetrofitListener rxRetrofitListener){
        //通过方法  得到RetrofitIView对象
        RetrofitIView retrofitIView = getRetrofitIViewMethod();
        //通过对象调用方法
        retrofitIView.getPostMapBody(map,url)
                .throttleFirst(2,TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserverMethod(rxRetrofitListener));
        return instance;
    }

    public RxRetrofitUtils doGetMapSomeThing(String url,Map<String,String> map,String userId, String sessionId, RxRetrofitListener rxRetrofitListener){
        //通过方法  得到RetrofitIView对象
        RetrofitIView retrofitIView = getRetrofitIViewMethod();
        //通过对象调用方法
        retrofitIView.getSomeThingMap(url,map,userId,sessionId)
                .throttleFirst(10,TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserverMethod(rxRetrofitListener));
        return instance;
    }


    /**
     * 0 创建一个得到RetrofitIView对象的方法
     *
     *    APIBaseOuternetUrl 当前为外网环境地址
     *
     * */
    private RetrofitIView getRetrofitIViewMethod() {
        //API.APIBaseOuternetUrl  外网
        //API.APIBaseIntranetUrl  内网
        Retrofit retrofit = rbuilder.baseUrl(API.APIBaseOuternetUrl).build();
        RetrofitIView retrofitIView = retrofit.create(RetrofitIView.class);
        return retrofitIView;
    }


    /**
     * 5.创建一个观察者
     *
     * @param rxRetrofitListener   接口回调对象
     *
     * */
    private Observer getObserverMethod(final RxRetrofitListener rxRetrofitListener){

        Observer observer = new Observer<ResponseBody>(){
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println(d.isDisposed());
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                //成功得到数据
                try {
                    String result = responseBody.string();
                    if(!TextUtils.isEmpty(result)){
                        //判断非空
                        if(rxRetrofitListener!=null){
                            rxRetrofitListener.onSuccessU(result);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if(rxRetrofitListener!=null){
                        rxRetrofitListener.onFailed(e.getMessage());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                //失败的时候
                if(rxRetrofitListener!=null){
                    rxRetrofitListener.onFailed(e.getMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        };
        return observer;
    }

    /**
     * 定义一个接口
     *
     * */
    public interface RxRetrofitListener{
       void onSuccessU(String data);//成功的方法
       void onFailed(String message);//失败的方法
    }

}
