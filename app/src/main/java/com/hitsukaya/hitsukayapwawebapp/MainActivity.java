package com.hitsukaya.hitsukayapwawebapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity
{
    private WebView webView;
    private String URL = "https://mafautoful.hs4m.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomerWebViewclient client = new CustomerWebViewclient( this);

        webView = findViewById(R.id.web_view);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webView.setWebViewClient(client);
        webView.loadUrl(URL);



    }

    class CustomerWebViewclient extends WebViewClient
    {
        private Activity activity;
        CustomerWebViewclient(Activity activity)
        {
            this.activity = activity;
        }


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.contains(URL))
            {
                view.loadUrl(url);
            }
            else
            {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
            }
            return true;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && this.webView.canGoBack())
        {
            this.webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        if (webView.canGoBack())
        {
            webView.goBack();

        }


        super.onBackPressed();

    }
}