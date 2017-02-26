package com.rent.mytestapplication.retrofit.interceptor;

import android.util.Log;

import java.io.EOFException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * <p/>OkHttp拦截器，用来打印网络请求日志
 * <p>
 * 打印格式：<br/>
 * method url: www.gaiay.cn<br/>
 * body: userId=xxxxx<br/>
 * response code: 200, cost time: 10ms<br/>
 * result: {json}
 * <p/>
 * Created by RenTao on 16/10/20.
 */
public class NetworkInfoInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        StringBuilder builder = new StringBuilder();

        // append method and url
        builder.append(request.method() + " url: " + request.url());

        // append body
        this.printBody(request, builder);

        long begin = System.currentTimeMillis();
        Response response = chain.proceed(request);
        long duration = System.currentTimeMillis() - begin;

        // append response code and cost time
        builder.append("\nresponse code: " + response.code() + ", cost time: " + duration + "ms");
        ResponseBody responseBody = response.body();
        // append response body if success
        if (response.isSuccessful()) {
            if (responseBody.contentLength() != 0) {
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE);
                Buffer buffer = source.buffer();
                if (isPlaintext(buffer)) {
                    builder.append("\nresult: " + buffer.clone().readString(Charset.forName("UTF-8")));
                }
            }
        } else {
            builder.append("\nmessage: " + response.message());
        }
        // print it
        Log.d("NetworkInfoInterceptor", builder.toString());
        return response;
    }

    private String decode(String str) {
        try {
            str = str.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
            str = str.replaceAll("\\+", "%2B");
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void printBody(final Request request, StringBuilder builder) {
        try {
            RequestBody requestBody = request.body();
            if (requestBody == null || !(requestBody instanceof FormBody)) {
                return;
            }
            FormBody body = (FormBody) requestBody;
            if (body.size() <= 0) {
                return;
            }
            builder.append("\nbody: ");
            for (int i = 0; i < body.size(); i++) {
                if (i > 0) {
                    builder.append(", ");
                }
//                builder.append(body.encodedName(i) + "=" + decode(body.encodedValue(i)));
                builder.append(body.encodedName(i) + "=" + body.encodedValue(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns true if the body in question probably contains human readable text. Uses a small sample
     * of code points to detect unicode control characters commonly used in binary file signatures.
     */
    private boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }
}