package com.example.baobang.gameduangua.data;

public class ApiUtils {
    public static final String BASE_URL = "https://horseracingnodejs.herokuapp.com";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}
