package com.retrofit;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Response;


public interface APICallback {

	// response JSON Object
	void onResponse(Call<JsonObject> call, Response<JsonObject> response);
	void onSDPError(Call<JsonObject> call, Response<JsonObject> response);
	void onFailure(Call<JsonObject> call, Throwable t);



}
