package com.akakim.retrofit;

import androidx.annotation.NonNull;

/**
 * Network 에서 Key-value 방식으로 흔히들 이용한다.
 * 1. 단어를 그대로 쓴건 서버와의 키를 빠르기 비교가 가능하고
 * 2. 상수값을 사용한건 오타의 가능성을 줄이기 위해서다.
 * -- 키값을 잘못쓰고 시간을 낭비해보면 그 기분을 잘 이해할 것이다.
 * */
public class CommonConstant {


	public static String OTP_EVENT_COUNT= "otpEC";
	public static String RESULT_CODE = "resultCode";
	public static String RESULT_MSG = "resultMsg";

	public static String MGR_ADDR = "mgrAddr";
	public static String TOKEN = "token";
	public static String SECURTY_TYPE = "securityType";

	/**
	 * Enum 클래스를 만들어서 굳이 이렇게 표현하는 이유.
	 * 1.
	 * Retrofit2 에서 @Tag가 아예 없다.
	 * Request를 만들때 Intercepter를 믿을 수 없다.
	 * 그래서 새롭게 생각한 것이.
	 * 응답할 때 콜백에서 인자값을 던져서 검증하면 될것 아닌가.
	 *
	 * 2.
	 * 컴파일 단계에서 String으로 인자값을 넘길 수 있다.
	 * String으로 한다면, 2가지 방법이 있다.
	 * 위에서 TOKEN처럼 상수 값을 넣을 수 있다.
	 * 이때의 실수 가능성은 상수 값을 이용한 맥락을 모를 수도 있다.
	 * "token" 과 같이 하드코딩을 할수도 있다.
	 * 이때의 실수는 스펠링을 틀릴 가능성이 있다.
	 * 두가지다 실수의 가능성이 있다. 그렇기 때문에 해당 클래스를 남겼다.
	 **/
	public enum API{
		KEY("API_ID"),
		AUTHENTICATE("API_AUTHENTICATE"),
		LOGIN("API_LOGIN"),
		GET_IPSEC_CONF("API_GET_IPSEC_CONF"),
		DEFINE_IPSEC ( "API_DEFINE_IPSEC" );
		String value;
		API(String value){
			this.value = value;
		};


		public boolean isEqual(API api){
			return this.value.equals(api.value);
		}

		@NonNull
		@Override
		public String toString() {
			return value;
		}
	}
}
