package com.yuepang.yuepang.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.yuepang.yuepang.Util.LogUtils;
import com.yuepang.yuepang.activity.BaseActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by xugh on 2019/3/20.
 */

public class HttpEngine {

    private BaseActivity baseActivity;

    private boolean isCanceled;

    public HttpEngine(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    public String executePost(String request, String url) {
        HttpURLConnection con = null;
        LogUtils.v("request ==================>> " + url);
        LogUtils.v(request);
        if (isCanceled()) {
            LogUtils.w("http request canceled !!");
            return null;
        }
        byte[] sendData = request.getBytes();
        try {
            if (url.startsWith("https://")) {
                con = createHttpsConnection(url);
            } else {
                con = (HttpURLConnection) new URL(url).openConnection();
            }
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setConnectTimeout(10000);
            con.setReadTimeout(15000);
            con.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            con.setRequestProperty("Charset", "UTF-8");
            con.setRequestProperty("Content-Type", "text/plain; charset=UTF-8");
            con.setUseCaches(false);
            con.setRequestProperty("Content-length", "" + sendData.length);
            OutputStream os = con.getOutputStream();
            os.write(sendData);
            os.close();
            int httpCode = con.getResponseCode();
            if (httpCode == HttpURLConnection.HTTP_OK) {
                if (isCanceled()) {
                    LogUtils.w("http request canceled !!");
                    return null;
                }
                InputStream is = con.getInputStream();
                ByteArrayOutputStream bab = new ByteArrayOutputStream(1024);
                int line = -1;
                byte[] buffer = new byte[1024];
                while ((line = is.read(buffer)) != -1) {
                    bab.write(buffer, 0, line);
                }
                byte[] encryData = bab.toByteArray();
                String result = new String(encryData, "utf-8");
                LogUtils.v("response =================>> " + url);
                LogUtils.v(result);
                LogUtils.v("response <<================= " + url);
                return result;
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return null;
    }

    @NonNull
    private HttpURLConnection createHttpsConnection(String url) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        HttpURLConnection con;
        con = (HttpsURLConnection) new URL(url).openConnection();
        TrustManager[] tm = {MyX509TrustManager};
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tm, new java.security.SecureRandom());
        // 从上述SSLContext对象中得到SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        ((HttpsURLConnection) con).setSSLSocketFactory(ssf);
        ((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        return con;
    }

    private static TrustManager MyX509TrustManager = new X509TrustManager() {

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
    };

    /**
     * 返回流，有参数
     *
     * @return
     * @throws IOException
     */
    public static InputStream createGetRequest(Context context, String url) throws IOException {
        LogUtils.e("market downloadGet url:" + url);
        if (!hasNetwork(context)) {
            LogUtils.e("market downloadGet net is unavailable!");
            return null;
        }
        InputStream is = null;
        long length = 0;
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setDoInput(true);
            con.setRequestMethod("GET");
            con.setConnectTimeout(10 * 1000);
            con.setReadTimeout(10 * 1000);
            con.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            con.setRequestProperty("Charset", "UTF-8");
            con.setRequestProperty("Content-Type", "text/plain; charset=UTF-8");
            int code = con.getResponseCode();
            LogUtils.e("code" + code);
            if (code == HttpURLConnection.HTTP_OK) {
                is = con.getInputStream();
                length = con.getContentLength();
                return is;
            }


        } catch (Exception e) {
            LogUtils.e(e);
        }
        return null;
    }





    /**
     * @param context
     * @return
     */
    public static boolean hasNetwork(Context context) {
        ConnectivityManager con = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo workinfo = con.getActiveNetworkInfo();
        if (workinfo == null || !workinfo.isAvailable()) {
            LogUtils.e(" market hasNetwork 网络异常");
            return false;
        } else {

            LogUtils.e(" market hasNetwork 网络良好");
        }
        return true;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

}
