package dario.androidclient;

/*
The MIT License (MIT)

Copyright (c) <2015> <Dario A Lencina Talarico>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/

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