package com.akakim.retrofit;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Response;


public interface APICallback {
	// JSON Object 형태로 정상응답주는 경우
	void onJsonObjectResponse(Call<JsonObject> call, Response<JsonObject> response);

	// JSON Array로 형태로 정상응답주는 경우
	void onJsonArrayResponse(Call<JsonArray> call, Response<JsonArray> response);

	// JSON Object 형태로 SDP상의 오류를 처리하는 경우 (또는 비즈니스 로직에서의 오류 처리와 같다. )
	void onJsonObjectSDPError(Call<JsonObject> call, Response<JsonObject> response);

	// JSON Array 형태로 SDP상의 오류를 처리하는 경우 (또는 비즈니스 로직에서의 오류 처리와 같다. )
	void onJsonArraySDPError(Call<JsonArray> call, Response<JsonArray> response);

	// JSON Object 형태로 안드로이드 클라이언트에서 오류가 발생한 경우
	void onFailure(Call<JsonObject> call, Throwable t);

	// JSON Array 형태로 안드로이드 클라이언트에서 오류가 발생한 경우
	void onJsonArrayFailure(Call<JsonArray> call, Throwable t);
}
