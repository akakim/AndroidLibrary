package com.retrofit;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.JsonObject;
import com.util.DLog;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import okhttp3.Headers;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Response;

public class RetrofitUtil {


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void getAllRequestObject(Call<?> call){
        getRequestBodyDebug( call );
        getHeadersDebug( call );

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void getRequestBodyDebug(Call<?> call){
        if ( call == null){
            DLog.d("Request CallObject is null ");
        } else {
            Buffer buffer = new Buffer();

            try {
                call.request().body().writeTo( buffer );
                DLog.d( "login Result : " + buffer.readString(StandardCharsets.UTF_8) );

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void getHeadersDebug(Call<?> request){
        Headers headers = request.request().headers();
        if( headers == null || headers.names() == null){
            DLog.e ( "header is null ");
        }else {
            for (int k = 0; k < headers.names().size(); k++) {
                DLog.d("header Key : " + headers.name(k));
                DLog.d("header value : " + headers.get(headers.name(k)));
            }
        }

    }

//	public static String readBody(Response<?> response){
//
//		if ( response == null )
//			return "response null";
//
//		if (response.raw() == null){
//			return "response raw body is null";
//		}
//		Reader reader = response.body().finalize();
//		char[] buffer = new char[4096];
//		StringBuilder builder = new StringBuilder();
//		int numChars;
//
//		try {
//			while ((numChars = reader.read(buffer)) >= 0) {
//				builder.append(buffer, 0, numChars);
//			}
//		}catch (Exception e ) {
//			e.printStackTrace();
//			return "Unexpected Exception";
//		}
//		return builder.toString();
//	}

    public static String readErrorBody(Response<?> response){

        if ( response == null )
            return "response null";

        if (response.errorBody() == null){
            return "response error body is null";
        }
        Reader reader = response.errorBody().charStream();
        char[] buffer = new char[4096];
        StringBuilder builder = new StringBuilder();
        int numChars;

        try {
            while ((numChars = reader.read(buffer)) >= 0) {
                builder.append(buffer, 0, numChars);
            }
        }catch (Exception e ) {
            e.printStackTrace();
            return "Unexpected Exception";
        }
        return builder.toString();
    }

    public static String NullToString(Response<JsonObject> response, String key){
        if ( response.body().get( key ) == null || response.body().get( key ).isJsonNull() ){
            return "";
        }else {
            return response.body().get( key ).getAsString();
        }
    }

    public static Integer NullToZero(Response<JsonObject> response, String key){
        if ( response.body().get( key ) == null || response.body().get( key ).isJsonNull() ){
            return 0;
        }else {
            return response.body().get( key ).getAsInt();
        }
    }

}
