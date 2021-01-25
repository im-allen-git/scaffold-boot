package com.allenjiang.scaffold.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.kairong.cbtbatch.util
 * @date:2020-10-13
 */
@Slf4j
public class OkHttpsUtil {

    private static OkHttpClient client;

    private static volatile int totalCount = 0;

    static {
        initClient();
    }


    private static void initClient() {
        client = new OkHttpClient.Builder().connectTimeout(600, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).build();
    }


    private static synchronized void checkAndInit() {
        if (totalCount > 0 && totalCount % 100 == 0) {
            client = new OkHttpClient.Builder().connectTimeout(600, TimeUnit.SECONDS)
                    .readTimeout(300, TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).build();
        }
    }

    /**
     * 调用URL（Get）
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static JSONObject callUrlByGet(String url) throws IOException {

        checkAndInit();
        Request request = new Request.Builder().addHeader("Connection", "close").addHeader("Accept", "*/*").addHeader("Connection", "close")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)").url(url).build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("response is not successful");
        }
        return response.body() != null ?
                JSON.parseObject(response.body().string()) : null;
    }


}
