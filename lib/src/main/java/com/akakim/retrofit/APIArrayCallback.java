package com.akakim.retrofit;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.Response;

public interface APIArrayCallback {
	void onResponse(Call<JsonArray> call, Response<JsonArray> response);
	void onSDPError(Response<JsonArray> response);
	void onFailure(Call<JsonArray> call, Throwable t);
}
