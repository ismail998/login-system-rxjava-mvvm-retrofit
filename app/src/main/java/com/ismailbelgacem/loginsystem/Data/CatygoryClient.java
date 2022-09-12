package com.ismailbelgacem.loginsystem.Data;

import com.ismailbelgacem.loginsystem.Model.Catygory;
import com.ismailbelgacem.loginsystem.Model.Login;
import com.ismailbelgacem.loginsystem.Model.ResponceLogin;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CatygoryClient {
    private static final String BaseUrl="https://satatechnologygroup.net:3301/api/";
    private SystemInterFace systemInterFace;
    private static CatygoryClient INSTANCE;
    public CatygoryClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl).client(getUnsafeOkHttpClient().build())
                .addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        systemInterFace = retrofit.create(SystemInterFace.class);
    }
    public static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static CatygoryClient getINSTANCE() {
        if (INSTANCE==null){
            INSTANCE =new CatygoryClient();
        }
        return INSTANCE;
    }
    public Observable<ArrayList<Catygory>> getAllCatygory(){
        return systemInterFace.getAllCatygory();
    }
}
