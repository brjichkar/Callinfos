package com.neotrick.callinfos.home_section.help_section;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.neotrick.callinfos.R;

public class WebviewActivity extends AppCompatActivity {

    private final static int FILECHOOSER_RESULTCODE = 1;
    private ValueCallback<Uri[]> mUploadMessage;
    WebView mainWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        mainWebView = findViewById(R.id.webView1);

        mainWebView.setWebViewClient(new MyWebViewClient());
        mainWebView.setWebChromeClient(new MyWebChromeClient());

        mainWebView.getSettings().setJavaScriptEnabled(true);
        mainWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mainWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mainWebView.getSettings().setJavaScriptEnabled(true);
        mainWebView.getSettings().setLoadWithOverviewMode(true);
        mainWebView.getSettings().setUseWideViewPort(true);
        mainWebView.getSettings().setBuiltInZoomControls(true);
        mainWebView.getSettings().setDomStorageEnabled(true);
        mainWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        mainWebView.setWebViewClient(new WebViewClient());
        mainWebView.loadUrl("https://callinfos.com/web/dashboard/2");
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (mainWebView.canGoBack()) {
                        mainWebView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == FILECHOOSER_RESULTCODE) {

            if (null == mUploadMessage || intent == null || resultCode != RESULT_OK) {
                return;
            }

            Uri[] result = null;
            String dataString = intent.getDataString();

            if (dataString != null) {
                result = new Uri[]{Uri.parse(dataString)};
            }

            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        }
    }
    private class MyWebViewClient extends WebViewClient {

        // permite la navegacion dentro del webview
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {

            if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(null);
            }

            mUploadMessage = filePathCallback;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("*/*"); // set MIME type to filter

            WebviewActivity.this.startActivityForResult(Intent.createChooser(i, "File Chooser"), WebviewActivity.FILECHOOSER_RESULTCODE );

            return true;
        }
    }

}