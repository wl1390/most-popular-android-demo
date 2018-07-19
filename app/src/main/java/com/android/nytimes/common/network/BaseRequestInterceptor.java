package com.android.nytimes.common.network;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class BaseRequestInterceptor implements Interceptor {

    private java.util.Map<String, String> requestHeaderValues;

    public BaseRequestInterceptor(Map<String, String> headerValues) {
        requestHeaderValues = headerValues;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder newBuilder = original.newBuilder();

        if (requestHeaderValues != null && requestHeaderValues.size() > 0) {
            final Set<String> keys = requestHeaderValues.keySet();
            for (final String key : keys) {
                newBuilder.addHeader(key, requestHeaderValues.get(key));
            }
            requestHeaderValues.clear();
        }
        // Customize the request
        Request request = newBuilder
                //check needed default headers
//                .header("Accept", "application/json")
//                .header("Authorization", "auth-token")
//                .header("ContentType", X_CONTENT_TYPE)
                .method(original.method(), original.body())
                .build();

        // Customize or return the response
        return chain.proceed(request);
    }

    public void setRequestHeaderValues(Map<String, String> headers){
        this.requestHeaderValues = headers;
    }
}
