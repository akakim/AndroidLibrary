package com.akakim.retrofit;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * JsonObjectCommonCallback.java -> APICallback.java 로 데이터를 전달함 진행할 예정.
 * 추후 서버의 진행하는 것에 대해 유연하게 대처할 수있도록 구현할 예정.
 * 예를 들면, 에러처리의 경우 서버에서 HTTP 코드로 줄수도 있다.
 * 아니면 정상응답(HTTP 200코드)를 준 다음. resultCode로 처리할 수있다.
 * 위의 2가지 사항을 고려해서 onResponse에서 유연하게 대처할 수 있다.
 */
public class JsonObjectCommonCallback implements Callback<JsonObject> {

	APICallback apiCallback;

	CommonConstant.API apiID;
	public JsonObjectCommonCallback(APICallback apiCallback , CommonConstant.API apiID) {
		this.apiCallback = apiCallback;
		this.apiID = apiID;
	}

	@Override
	public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
		if (response.code() == 200 ){

			Integer resultCode = response.code();
				if( resultCode == 200){
					apiCallback.onJsonObjectResponse( call ,  addAPIID (response,apiID ));

				} else {
					apiCallback.onJsonObjectSDPError( call, addAPIID (response,apiID ));
				}

			} else {
				apiCallback.onJsonObjectSDPError( call,  addAPIID (response,apiID ));
			}
	}

	@Override
	public void onFailure(Call<JsonObject> call, Throwable t) {
		apiCallback.onFailure( call,  t );
	}

	/**
	 * HTTP header에는 Request 를 구분할 수 있는 id 값을 넣을 수 없기 때문에
	 * 이 함수를 제작함. (참고 Header의 TAG에 id 를 넣어봤지만, Protector가 에러로 처리됬다. )
	 * response 시점에서 id를 넣어서 구분하도록 하는 기능이다.
	 * @param response
	 * @param id 변수값을 틀릴 수 있으니 방지하기 위해서 Enum으로 생성함.
	 * @return
	 */
	public Response<JsonObject> addAPIID(Response<JsonObject> response , CommonConstant.API id){

		if( response.body() == null ){
			return response;
		} else {
			response.body().addProperty(CommonConstant.API.KEY.toString(),id.toString());
			return response;
		}
	}
}
