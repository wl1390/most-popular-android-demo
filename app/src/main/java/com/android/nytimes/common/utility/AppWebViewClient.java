package com.android.nytimes.common.utility;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by Nibedita on 12/02/2018.
 */

public class AppWebViewClient extends WebViewClient {

    private ProgressBar progressBar;

    public AppWebViewClient(ProgressBar progressBar){
        this.progressBar = progressBar;

    }
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        this.progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        this.progressBar.setVisibility(View.GONE);
    }
}
