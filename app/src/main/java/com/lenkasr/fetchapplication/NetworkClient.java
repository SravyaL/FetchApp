package com.lenkasr.fetchapplication;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;


public class NetworkClient {

    private static final OkHttpClient client = new OkHttpClient();

    public static void fetchJson(String url, final NetworkCallback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull okhttp3.Call call, @NonNull okhttp3.Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    // Parse the response
                    String json = response.body().string();
                    callback.onSuccess(json);
                } else {
                    // Handle unsuccessful response
                    callback.onFailure(new IOException("Unexpected code " + response));
                }
            }

            @Override
            public void onFailure(@NonNull okhttp3.Call call, @NonNull IOException e) {
                callback.onFailure(e);
            }


        });
    }

    public interface NetworkCallback {
        void onSuccess(String json);

        void onFailure(Exception e);
    }
}


