package com.wd.tech.utils.datarequestutil;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;


/**
 * Author : 张自力
 * Created on time.
 *
 * Retrofit 接口
 */

public interface RetrofitIView {

    /**
     *  1.普通的Get 方式
     *
     *  @param url 为 除了网络环境外的后半段地址
     *
     * */
    @GET
    Observable<ResponseBody> doGet(@Url String url);

    /**
     * 2.post 以body方式进行提交
     *
     * @param requestBody  请求体
     * @param url 为 除了网络环境外的后半段地址
     *
     * */
    @POST
    Observable<ResponseBody> getPostBody(@Body RequestBody requestBody, @Url String url);

    /**
     * 3.post 以MultipartBody方式进行提交
     *   应用场景  上传头像
     *
     * @param multipartBody  multipartBody体
     * @param url 为 除了网络环境外的后半段地址
     *
     * */
    @Multipart
    @POST
    Observable<ResponseBody> getPostUploadHeadPicFile(@Part MultipartBody.Part multipartBody, @Url String url);

    /**
     * 4.post 以map方式进行提交
     *
     * @param map<String,String>  map集合
     * @param url 为 除了网络环境外的后半段地址
     * */
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> getPostMapBody(@FieldMap Map<String,String> map, @Url String url);

}
