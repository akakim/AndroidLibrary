package com.dream.kimlibrary.common;


import android.content.Context;
import androidx.annotation.RawRes;

import com.dream.kimlibrary.activity.User;
import com.google.gson.Gson;
import com.akakim.util.StringUtil;

import java.io.IOException;


public class ParseTask extends Thread{

    ThreadCallback callback;
    Context context;

    @RawRes
    int fileID;

    /**
     *
     * @param callback
     * @param context activity에서 getApplicaionContext를 이용한다.
     * @param fileID
     */
    public ParseTask(ThreadCallback callback, Context context, @RawRes int fileID) {
        this.callback = callback;
        this.context = context;
        this.fileID = fileID;
    }


    @Override
    public synchronized void start() {
        super.start();

        try {
            String parseReuslt = StringUtil.getJSONFromFile( context,fileID );
            Gson gson = new Gson();
            User[] userArray = gson.fromJson(parseReuslt, User[].class);

/*            for(User user : userArray) {
                System.out.println(user);
            }*/

            callback.responseUser(userArray);

        } catch (IOException e) {
            e.printStackTrace();

            callback.onError( e.getMessage() );
        }




    }

    public void parsingUSERJSONArray() throws IOException {

       /* InputStream is = getResources().openRawResource(R.raw.user);
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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if( is != null )
                is.close();
        }*/

//        String jsonString = writer.toString();
    }


}
