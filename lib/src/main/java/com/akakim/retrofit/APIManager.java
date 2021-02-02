package com.akakim.retrofit;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.strongswan.android.R;
import org.strongswan.android.data.CommonConstant;
import org.strongswan.android.data.SDPService;
import org.strongswan.android.utils.DLog;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * API 요청에 대한 모든 것.
 */
public class APIManager {

	// Agent 위치인 안드로이드 에서는 무조건 Protector로 보낸다.
	private static final String TIN_URL = "https://15.164.75.151:8443/m3p-np/"; // Protector 임.
	private static String AFTER_LOGIN_TIM_URL = "";
	private static Context context;
	private static org.strongswan.android.network.APIManager instance;
	private volatile String token ="";
	private volatile String aid= "";

	GsonBuilder gsonBuilder = new GsonBuilder();


	public static org.strongswan.android.network.APIManager getInstance(){

		if( instance == null){
			instance = new org.strongswan.android.network.APIManager();
		}
		return instance;
	}

	/**
	 * 단 한번만 호출하기. getInstance 호출 이후
	 * @param context
	 */
	public void init(Context context){
		org.strongswan.android.network.APIManager.context = context;

	}

	// API 요청은 화면의 Context가 아닌 Application의 Constext를 무 조 건 넣는다.
	private SDPService getSDPService(){
		try {
		Retrofit retrofit = new Retrofit.Builder()
			.baseUrl(TIN_URL)
			.client( getUnsafeOkHttpClient().build())
			.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
			.build();

		return retrofit.create( SDPService.class ) ;
		} catch (Exception e ) {
			e.printStackTrace();
			Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(TIN_URL)
				.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
				.build();

			return retrofit.create( SDPService.class ) ;
		}
	}

	// API 요청은 화면의 Context가 아닌 Application의 Constext를 넣는다.
	private SDPService getSDPServiceUsingToken(){
		GsonBuilder testBuilder = new GsonBuilder();
		testBuilder.serializeNulls();
		if("".equals(AFTER_LOGIN_TIM_URL)){
			// AFTER_LOGIN_TIM_URL 서버 base URL을 고려하여 만듬.
			AFTER_LOGIN_TIM_URL = TIN_URL.substring(0,TIN_URL.length()-2);
			DLog.i( "TIM URL " + AFTER_LOGIN_TIM_URL);
		}

		try {

			Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(AFTER_LOGIN_TIM_URL +"/")
				.client( getUnsafeHTTPSUseTokenClient().build())
				.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
				.build();

			return retrofit.create( SDPService.class ) ;
		} catch (Exception e ) {
			e.printStackTrace();
			Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(AFTER_LOGIN_TIM_URL + "/")
				.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
				.build();
			return retrofit.create(SDPService.class);
		}
	}

	// 안전하지 않음으로 HTTPS를 이용한다. Header에 Token을 이용한 값을 제공한다.
	public OkHttpClient.Builder getUnsafeHTTPSUseTokenClient() {
		try {
			OkHttpClient.Builder builder = new OkHttpClient.Builder();

			Interceptor interceptor = new Interceptor() {
				Request newRequest;
				@Override
				public okhttp3.Response intercept(Chain chain) throws IOException {

					if (token != null && !token.equals("")) { // 토큰이 없는 경우
						// x-access-token

						newRequest = chain.request().newBuilder()
							.addHeader(	"Content-Type", "application/json")
							.addHeader("x-access-token", token)
							.addHeader("x-access-aid", aid)
							.build();

						//newRequest = chain.request();
						DLog.i( "API 요청시 token 값 : " + newRequest.header( "x-access-token") );

						DLog.i( "APi 요청시 AID 요청값 : "+newRequest.header( "x-access-aid") );
						return chain.proceed(newRequest);
					} else {
						DLog.i(" token is null or empty" );
						newRequest = chain.request();

					}
					return chain.proceed(newRequest);

				}

			};

			builder.interceptors().add(interceptor);

			final TrustManager[] trustAllCerts = new TrustManager[]{
				new X509TrustManager() {
					@Override
					public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

					}

					@Override
					public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

					}

					@Override
					public X509Certificate[] getAcceptedIssuers() {
						return new X509Certificate[]{};
					}
				}
			};

			final SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

			final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

			builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
			builder.hostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});

			return builder;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	// 안전하지 않음으로 HTTPS를 통과합니다.
	public static OkHttpClient.Builder getUnsafeOkHttpClient() {
		try {
			final TrustManager[] trustAllCerts = new TrustManager[]{
				new X509TrustManager() {
					@Override
					public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

					}

					@Override
					public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					}

					@Override
					public X509Certificate[] getAcceptedIssuers() {
						return new X509Certificate[]{};
					}
				}
			};

			final SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

			final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

			OkHttpClient.Builder builder = new OkHttpClient.Builder();

			builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
			builder.hostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
			return builder;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * localhost 라는 인증서 정보로 SSL 연결을 시도한다.
	 * @param context
	 */
	public static SSLSocketFactory getPinnedCertSSLSocketFactory(Context context){
		InputStream caInput = null;
		try{

			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			caInput = context.getResources().openRawResource(R.raw.local_tomcat);
			Certificate ca = null;
			ca = cf.generateCertificate( caInput );
			System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());

			String keyStoreType = KeyStore.getDefaultType();
			KeyStore keyStore = KeyStore.getInstance(keyStoreType);
			keyStore.load(null, null);
			if (ca == null) {
				return null;
			}

			keyStore.setCertificateEntry("ca", ca);

			String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
			tmf.init(keyStore);

			SSLContext sslContext= SSLContext.getInstance("TLS");
			sslContext.init(null, tmf.getTrustManagers(), null);

		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (caInput != null){
				try {
					caInput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	/**
	 *
	 * @param otpPassword HOTP 값
	 * @param apiCallback
	 */
	public void authenticate(String otpPassword, String acId, String acPw, APICallback apiCallback){
		SDPService service = getSDPService();

		HashMap<String, Object> obj = new HashMap<>();
			obj.put("aid",aid);
			obj.put("password",otpPassword);
			obj.put("ac_id",acId);
			obj.put("ac_pw",acPw);

		Call<JsonObject> authRequest = service.authenticate( obj );

		org.strongswan.android.network.JsonObjectCommonCallback callback= new org.strongswan.android.network.JsonObjectCommonCallback(apiCallback, org.strongswan.android.data.CommonConstant.API.AUTHENTICATE);
		authRequest.enqueue( callback );
	}

	/**
	 *
	 * @return
	 */
	public void login( APICallback apiCallback){
		SDPService service = getSDPServiceUsingToken();

		HashMap<String, Object> obj = new HashMap<>();

		obj.put("aid",aid);
		obj.put("token",token);

		Call<JsonObject> authRequest = service.login(  obj );

		org.strongswan.android.network.JsonObjectCommonCallback callback= new org.strongswan.android.network.JsonObjectCommonCallback(apiCallback, org.strongswan.android.data.CommonConstant.API.LOGIN);
		authRequest.enqueue( callback );

	}

	/**
	 *
	 * @return
	 */
	public void ipsecDefine(String aid, APICallback apiCallback){
		SDPService service = getSDPServiceUsingToken();

		HashMap<String, Object> obj = new HashMap<>();
			obj.put("aid",aid);
			obj.put("token",token);

		Call<JsonObject> authRequest = service.login(  obj );
		Headers headers = authRequest.request().headers();

		DLog.i( "token : " + token );

		org.strongswan.android.network.JsonObjectCommonCallback callback= new org.strongswan.android.network.JsonObjectCommonCallback(apiCallback, org.strongswan.android.data.CommonConstant.API.DEFINE_IPSEC);
		authRequest.enqueue( callback );

	}

	public void ipsecConf(APICallback apiCallback){
		SDPService service = getSDPServiceUsingToken();
		Call<JsonArray> ipsecRequest = service.getIPSecConf( aid );

		org.strongswan.android.network.JsonArrayCommonCallback commonCallback = new org.strongswan.android.network.JsonArrayCommonCallback(apiCallback, org.strongswan.android.data.CommonConstant.API.GET_IPSEC_CONF)
		ipsecRequest.enqueue(
			new Callback<JsonArray>() {
				@Override
				public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

					if( response.code() == 200){
						apiCallback.onJsonArrayResponse( call, response);
					} else {
						apiCallback.onJsonArrayResponse( call,response );
					}
				}

				@Override
				public void onFailure(Call<JsonArray> call, Throwable t) {
					apiCallback.onJsonArrayFailure(call,t);

				}
			}
		);

	}

	public boolean isAidExist(){
		return !aid.equals("");
	}

	public boolean isTokenExist(){
		return !token.equals("");
	}

	public String getTIM_URL() {
		return AFTER_LOGIN_TIM_URL;
	}

	public void setTIM_URL(String TIM_URL) {
		this.AFTER_LOGIN_TIM_URL = TIM_URL;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	/**
	 * HTTP header에는 Request 를 구분할 수 있는 id 값을 넣을 수 없기 때문에
	 * 이 함수를 제작함. (참고 Header의 TAG에 id 를 넣어봤지만, Protector가 에러로 처리됬다. )
	 * response 시점에서 id를 넣어서 구분하도록 하는 기능이다.
	 * @param response
	 * @param id 변수값을 틀릴 수 있으니 방지하기 위해서 Enum으로 생성함.
	 * @return
	 */
	public Response<JsonObject> addAPIID(Response<JsonObject> response , org.strongswan.android.data.CommonConstant.API id){

		if( response.body() == null ){
			return response;
		} else {
			response.body().addProperty(org.strongswan.android.data.CommonConstant.API.KEY.toString(),id.toString());
			return response;
		}
	}

	public void debugHTTPHeader(Request authRequest){
		Headers headers = authRequest.request().headers();

		if( headers.size() == 0 ){
			DLog.i ( "header size is 0 ");
		}
		for( int k = 0; k< headers.names().size(); k++){
			DLog.i ( "header Key : " + headers.name(k));
			DLog.i ( "header value : " + headers.get(headers.name(k) ));
		}

		Buffer buffer = new Buffer();

		try {
			authRequest.request().body().writeTo( buffer );
			DLog.i( "login Request 바디.내용  " + buffer.readString(StandardCharsets.UTF_8) );

		} catch (IOException e) {
			e.printStackTrace();
		}

		authRequest.request().body();
		DLog.i( "token : " + token );
	}
}
