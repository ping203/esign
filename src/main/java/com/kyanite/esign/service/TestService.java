package com.kyanite.esign.service;


import okhttp3.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestService {

    public static void main(String[] args) throws Exception {
        String accessKeyId = "imRaVJn13K91q0zE";
        String accessKeySecret = "mtNR5QShYwHePmgSZXJsgrJGX5Nu7qFD";

        String format = "json";
        String timestamp = "20201102";
        String version = "1.0.0";

        Map<String, String> data = new HashMap<>();
        data.put("accessKeyId", accessKeyId);
        data.put("format", format);
        data.put("timestamp", timestamp);
        data.put("version", version);
        String signature = SignatureUtil.generateSignature(data, accessKeySecret);


//        DefaultHttpClient httpClient = new DefaultHttpClient();
//        ResponseHandler<String> responseHandler = new BasicResponseHandler();
//        HttpPost req = new HttpPost("https://hmo-test.fosun.com/api/oapi/goods/query");
//        req.setHeader("Content-Type", "application/json;charset=UTF-8");
//        req.setHeader("authorization", accessKeyId + ":" + signature);
//        req.setHeader("format", format);
//        req.setHeader("timestamp", timestamp);
//        req.setHeader("version", version);





//        OkHttpClient client = new OkHttpClient().newBuilder()
//            .build();
//        Request request = new Request.Builder()
//            .url("https://hmo-test.fosun.com/api/oapi/goods/query" )
//            .method("GET", null)
//            .addHeader("format", format)
//            .addHeader("timestamp", timestamp)
//            .addHeader("version", version)
//            .addHeader("Authorization", accessKeyId + ":" + signature)
//            .addHeader("Content-Type", "application/json;charset=UTF-8")
//            .build();

        OkHttpClient client = new OkHttpClient().newBuilder()
            .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"channelNo\":\"FUXINGTONG\",\"data\":{\"idNo\":\"32048219930218563x\",\"idType\":\"MAINLAND\",\"mobile\":\"13564314361\",\"name\":\"王俊杰\"},\"requestNo\":\"REQUEST202011021654439851017\",\"requestTime\":\"2020-11-02 16:54:43:999\"}");
        Request request = new Request.Builder()
            .url("http://esign-test.fosun.com/v2/user/addPersonalAccount")
            .method("POST", body)
            .addHeader("format", "json")
            .addHeader("timestamp", "20201102")
            .addHeader("version", "1.0.0")
            .addHeader("authorization", accessKeyId + ":" + signature)
            .addHeader("Content-Type", "application/json")
            .build();
        Response response = client.newCall(request).execute();

        System.out.println(request.headers().toString());
        System.out.println(response.body().string());
    }
}
