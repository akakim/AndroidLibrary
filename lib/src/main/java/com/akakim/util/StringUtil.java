package com.akakim.util;

import android.content.Context;

import androidx.annotation.RawRes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class StringUtil {

    /**
     *
     * @param context
     * @param id
     * @return "" 인경우는 에러처리 아닌경우 JSON 문자열이 올것임.
     * @throws IOException
     */
    public static String getJSONFromFile(Context context, @RawRes int id) throws IOException{
        InputStream is = context.getResources().openRawResource( id );

        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        } finally {
            if( is != null )
                is.close();
        }
        return writer.toString();
    }



}
