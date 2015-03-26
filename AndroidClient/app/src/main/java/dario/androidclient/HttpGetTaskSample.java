package dario.androidclient;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class HttpGetTaskSample extends AsyncTask<String, Void, String>
{
    private HttpCallback callback;

    public HttpGetTaskSample(HttpCallback callback) {
        this.callback = callback;
    }

    @Override
    protected String doInBackground(String... params)
    {
        String serverUrl = params[0];
        String result;
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet request = new HttpGet(URI.create(serverUrl));
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }else{
                result = "empty body";
            }
        } catch(Exception e) {
            result = e.getMessage();
        }
        return  result;
    }

    protected void onPostExecute(String result){
        this.callback.onComplete(result);
    }
}