package com.akakim.util;

import android.content.Context;
import android.util.TypedValue;

public class UIUtil {

    public static int DPToPX(Context context, float dp ){
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources().getDisplayMetrics());
        return px;

    }


    public static float PXToDP( Context context,float px){
        // 해상도 마다 다른 Density를 반환한다.
        // xxxhdpi density = 4
        float density = context.getResources().getDisplayMetrics().density;

        if( density == 1.0 ){   // mdpi ( 160 dpi ) xxxhdpi (density == 4) 기준으로 density 값을 재 설정한다.
            density *= 4.0;
        }else if (density == 1.5 ){
            density *= (8.0 /3);
        } else if ( density == 2.0){
            density *= 2.0;
        }

        return px / density; // dp 값 변환
    }
}
