package dangod.themis.core.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class DRequest {
    public static String sendGet(String url) {
        SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());
        HttpClient httpClient = new DefaultHttpClient();
        //創建一個httpGet方法
        HttpGet httpGet = new HttpGet(url);
        //設置httpGet的參數信息
        httpGet.setHeader("accept", "*/*");
        httpGet.setHeader("connection", "Keep-Alive");
        httpGet.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String result = null;
        if (response != null) {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                try {
                    HttpEntity entity = response.getEntity();
                    result = EntityUtils.toString(entity, "UTF-8");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }else{
            System.err.println("请求失败-返回空");
        }
        //關閉聯接
        httpClient.getConnectionManager().shutdown();
        return result;
    }

    public static String sendPost(String url, String jsonParams){
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-type", "application/json; charset=utf-8");
        httpPost.setHeader("Connection", "Close");
        //构建json参数
        StringEntity params = new StringEntity(jsonParams, Charset.forName("UTF-8"));
        params.setContentEncoding("UTF-8");
        params.setContentType("application/json");
        httpPost.setEntity(params);

        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String result = null;
        if (response != null) {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                try {
                    HttpEntity entity = response.getEntity();
                    result = EntityUtils.toString(entity, "UTF-8");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }else{
            System.err.println("请求失败-返回空");
        }
        //關閉聯接
        httpClient.getConnectionManager().shutdown();
        return result;
    }

    public static String sendPostFile(String url, File file, String jsonParams){
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addBinaryBody("media",file);
        multipartEntityBuilder.addTextBody("description", jsonParams);
        HttpEntity param = multipartEntityBuilder.build();


        //构建File参数
        httpPost.setEntity(param);

        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String result = null;
        if (response != null) {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                try {
                    HttpEntity entity = response.getEntity();
                    result = EntityUtils.toString(entity, "UTF-8");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }else{
            System.err.println("上传失败");
        }
        //關閉聯接
        httpClient.getConnectionManager().shutdown();
        return result;
    }

    public static String sendPostFile(String url, File file){
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addBinaryBody("media",file);
        HttpEntity param = multipartEntityBuilder.build();

        //构建File参数
        httpPost.setEntity(param);

        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String result = null;
        if (response != null) {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                try {
                    HttpEntity entity = response.getEntity();
                    result = EntityUtils.toString(entity, "UTF-8");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }else{
            System.err.println("上传失败");
        }
        //關閉聯接
        httpClient.getConnectionManager().shutdown();
        return result;
    }
}