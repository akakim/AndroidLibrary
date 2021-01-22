package com.akakim.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 *
 */
public class DLog {
    /**
     *디버깅을 할 때
     *어느 시점에서 에러가났는지를 확인할 수 있게
     * 설정한 태그임.
     */
    static String CONTEXT_TAG = "Default";
    static final String INIT_TAG = "Default";
    static Context context;

    static boolean isDebuggable = false;
    public static void init(Context context){
        DLog.context = context;

        isDebuggable = isDebuggable();
        isDebuggable = true;

    }

    public static void setContextTag(String tag){
        DLog.CONTEXT_TAG = tag;
    }

    public static void initContextTag(){
        DLog.CONTEXT_TAG = DLog.INIT_TAG;
    }

    /** Log Level Error **/
    public static final void e(String message) {
        if( DLog.isDebuggable ) Log.d(CONTEXT_TAG,message);
    }
    /** Log Level Warning **/
    public static final void w(String message) {
        if( DLog.isDebuggable ) Log.d(CONTEXT_TAG,message);
    }
    /** Log Level Information **/
    public static final void i(String message) {
        if( DLog.isDebuggable ) Log.i(CONTEXT_TAG, buildLogMsg(message));
    }
    /** Log Level Debug **/
    public static final void d(String message) {
        if( DLog.isDebuggable ) Log.d(CONTEXT_TAG, buildLogMsg(message));
    }
    /** Log Level Verbose **/
    public static final void v(String message) {
        if( DLog.isDebuggable ) Log.v(CONTEXT_TAG, buildLogMsg(message));
    }


    public static String buildLogMsg(String message) {

        StackTraceElement ste = Thread.currentThread().getStackTrace()[4];

        StringBuilder sb = new StringBuilder();

        sb.append("[");
        sb.append(ste.getFileName().replace(".java", ""));
        sb.append("::");
        sb.append(ste.getMethodName());
        sb.append("]");
        sb.append(message);

        return sb.toString();

    }

    /**
     * BuildConfig.DEBUG로 디버그 모드를 알 수  있는건
     *
     * @return
     */
    private static boolean isDebuggable() {
        boolean debuggable = false;

        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo appinfo = pm.getApplicationInfo(context.getPackageName(), 0);
            DLoggingInfo( "app info Flag " + appinfo.flags);
            DLoggingInfo( "app Info Debuggable " + ApplicationInfo.FLAG_DEBUGGABLE);
            DLoggingInfo( "app Info and operation" + (0 != (appinfo.flags & ApplicationInfo.FLAG_DEBUGGABLE)));

            debuggable = (0 == (appinfo.flags & ApplicationInfo.FLAG_DEBUGGABLE));
        } catch (PackageManager.NameNotFoundException e) {
            /* debuggable variable will remain false */
            isDebuggable = false;
        }

        if( isDebuggable ) {
            DLoggingInfo( "is Logging on");
        } else {
            DLoggingInfo( "is Logging on");
        }
        return debuggable;
    }

    private static void DLoggingInfo(String message){
        Log.i(DLog.class.getSimpleName(),message);
    }
}
