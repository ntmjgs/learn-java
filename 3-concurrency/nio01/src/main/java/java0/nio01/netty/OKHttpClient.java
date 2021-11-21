package java0.nio01.netty;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OKHttpClient {
    // 缓存客户端实例，设置超时
    public static OkHttpClient client = new OkHttpClient().newBuilder()
            .connectTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1,TimeUnit.SECONDS)
            .writeTimeout(1,TimeUnit.SECONDS)
            .build();


    // GET 调用
    public static String getAsString(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


    public static void main(String[] args) throws Exception {

        String url = "http://localhost:8801";
        String text = OKHttpClient.getAsString(url);
        System.out.println("url: " + url + " ; response: \n" + text);

        // 清理资源
        OKHttpClient.client = null;
    }
}
