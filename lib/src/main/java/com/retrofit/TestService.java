package com.retrofit;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TestService {

    @Headers({"Content-Type: application/json"})
    @POST("authenticate")
    Call<JsonObject> authenticate(@Body HashMap<String,Object> param);

    /**
     * 이렇게 헤더를 넣을 순 있지만,
     */
    @Headers("Content-Type: application/json")
    @PUT("device/session/login")
    Call<JsonObject> login(@Header("x-access-aid") String aid, @Header("x-access-token") String token , @Body HashMap<String,Object> param);

    @Headers("Content-Type: application/json")
    @POST("tun/ipspec")
    Call<JsonObject> ipSecDefine( @Header("x-access-aid") String aid, @Header("x-access-token") String token ,@Body HashMap<String,Object> param);

    @Headers("Content-Type: application/json")
    @GET("device/api/ipsec/{aid}")
    Call<JsonArray> getIPSecConf(@Header("x-access-aid") String xAccessAid, @Header("x-access-token") String token , @Path("aid") String aid);

}
