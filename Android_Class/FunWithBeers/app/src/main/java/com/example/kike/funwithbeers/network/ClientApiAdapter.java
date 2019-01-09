package com.example.kike.funwithbeers.network;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ClientApiAdapter {
    private static final String ROOT_URL = "https://restcountries.eu/rest/v2/";
    private static ClientApiService API_SERVICE;

    public static ClientApiService getApiService() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        if (API_SERVICE == null) {
            Retrofit mRetrofit = new Retrofit.Builder()
                    .baseUrl(ROOT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            API_SERVICE = mRetrofit.create(ClientApiService.class);
        }
        return API_SERVICE;
    }
//    public static RandomUserService getRandomUserService() {
//        return getRetrofitInstance().create(RandomUserService.class);
//    }

//    public static Call<FlagList> getContacts(int num) {
//        Call<FlagList> contacts = getRandomUserService().listContacts(num);
//        System.out.println(contacts);
//        return contacts;
//    }

}
