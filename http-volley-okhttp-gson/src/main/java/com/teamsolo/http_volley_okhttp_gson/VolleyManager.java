package com.teamsolo.http_volley_okhttp_gson;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.OkHttpClient;

/**
 * description: volley manager singleton
 * author: Melody
 * date: 2016/6/17
 * version: 0.0.0.1
 */
@SuppressWarnings("unused, WeakerAccess")
public enum VolleyManager {

    INSTANCE;

    VolleyManager() {
    }

    private static RequestQueue mRequestQueue;

    private static ImageLoader mImageLoader;

    public static void init(Context context) {
        mRequestQueue = Volley.newRequestQueue(context, new OkHttp3HurlStack(new OkHttpClient()));
        mImageLoader = new ImageLoader(mRequestQueue, new LruBitmapCache(context));
    }

    private <T> Request<T> add(Request<T> request) {
        return mRequestQueue.add(request);
    }

    public StringRequest stringRequest(Object tag, String url, Response.Listener<String> listener,
                                       Response.ErrorListener errorListener) {
        StringRequest request = new StringRequest(url, listener, errorListener);
        request.setTag(tag);
        add(request);

        return request;
    }

    public StringRequest stringRequest(Object tag, int method, String url,
                                       Response.Listener<String> listener,
                                       Response.ErrorListener errorListener) {
        StringRequest request = new StringRequest(method, url, listener, errorListener);
        request.setTag(tag);
        add(request);

        return request;
    }

    public ImageRequest imageRequest(Object tag, String url, Response.Listener<Bitmap> listener,
                                     int maxWidth, int maxHeight, ImageView.ScaleType scaleType,
                                     Bitmap.Config decodeConfig,
                                     Response.ErrorListener errorListener) {
        ImageRequest request = new ImageRequest(url, listener, maxWidth, maxHeight, scaleType,
                decodeConfig, errorListener);
        request.setTag(tag);
        add(request);
        return request;
    }

    public void imageLoaderRequest(ImageView imageView, String imgViewUrl, int defaultImageResId,
                                   int errorImageResId) {
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView,
                defaultImageResId, errorImageResId);
        mImageLoader.get(imgViewUrl, listener);
    }

    public void imageLoaderRequest(ImageView imageView, String imgViewUrl, int defaultImageResId,
                                   int errorImageResId, int maxWidth, int maxHeight) {
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView,
                defaultImageResId, errorImageResId);
        mImageLoader.get(imgViewUrl, listener, maxWidth, maxHeight);
    }

    public <T> GsonRequest<T> gsonGetRequest(Object tag, String url, Class<T> clazz,
                                             Response.Listener<T> listener,
                                             Response.ErrorListener errorListener) {
        GsonRequest<T> request = new GsonRequest<>(url, clazz, listener, errorListener);
        request.setTag(tag);
        add(request);

        return request;
    }

    public <T> GsonRequest<T> gsonPostRequest(Object tag, Map<String, String> params, String url,
                                              Class<T> clazz, Response.Listener<T> listener,
                                              Response.ErrorListener errorListener) {
        GsonRequest<T> request = new GsonRequest<>(Request.Method.POST, params, url, clazz,
                listener, errorListener);
        request.setTag(tag);
        add(request);

        return request;
    }

    public void jsonPostRequest(Object tag, String url, JSONObject jsonObject,
                                Response.Listener<JSONObject> listener,
                                Response.ErrorListener errorListener) {
        JsonObjectRequest jsonObjectRequest;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                listener, errorListener);
        jsonObjectRequest.setTag(tag);
        add(jsonObjectRequest);
    }

    public void cancel(Object tag) {
        mRequestQueue.cancelAll(tag);
    }
}
