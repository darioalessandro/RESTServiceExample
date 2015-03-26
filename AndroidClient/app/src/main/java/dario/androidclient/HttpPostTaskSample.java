package dario.androidclient;

import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpPostTaskSample extends AsyncTask<String, Void, String>
{
    private HttpCallback callback;

    public HttpPostTaskSample(HttpCallback callback) {
        this.callback = callback;
    }

    @Override
    protected String doInBackground(String... params)
    {
        BufferedReader inBuffer = null;
        String url = params[0];
        String result;
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost request = new HttpPost(url);
            List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
            postParameters.add(new BasicNameValuePair("textToEcho", params[1]));

            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
                    postParameters);

            request.setEntity(formEntity);
            HttpResponse response = httpClient.execute(request);
            if (response != null) {
                result = EntityUtils.toString(response.getEntity());
            }else{
                result = "empty body";
            }

        } catch(Exception e) {
            result = e.getMessage();
        } finally {
            if (inBuffer != null) {
                try {
                    inBuffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  result;
    }

    protected void onPostExecute(String result){
        this.callback.onComplete(result);
    }
}