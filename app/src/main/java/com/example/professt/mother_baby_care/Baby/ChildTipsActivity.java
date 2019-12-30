package com.example.professt.mother_baby_care.Baby;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.professt.mother_baby_care.R;

public class ChildTipsActivity extends AppCompatActivity {

    WebView webView;
    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_tips);

        swipe = (SwipeRefreshLayout)findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                WebAction();
            }
        });

        WebAction();
    }

    public void WebAction(){

        webView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setAppCacheEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
//        webSettings.setUseWideViewPort(true);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setEnableSmoothTransition(true);

        webView.loadUrl("https://dailyhappykids.com/tag/health-tips-for-bangladesh/");
        swipe.setRefreshing(true);
        webView.setWebViewClient(new WebViewClient(){

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

//                String myText = "<!doctype html>\n" +
//                        "<html lang=\"en\">\n" +
//                        "<head>\n" +
//                        "  <meta charset=\"utf-8\">\n" +
//                        "  <meta name=\"apple-mobile-web-app-capable\" content=\"yes\">\n" +
//                        "  <meta name=\"mobile-web-app-capable\" content=\"yes\">\n" +
//                        "  <title>No Connection</title>  \n" +
//                        "\n" +
//                        "<!-- Stylesheets--> \n" +
//                        " <style type=\"text/css\">\n" +
//                        " body{\n" +
//                        "  background: #E1e1e1;\n" +
//                        "}\n" +
//                        "\n" +
//                        "#cloud{\n" +
//                        "  width: 300px;\n" +
//                        "  height: 120px;\n" +
//                        "  background: #676767;\n" +
//                        "\n" +
//                        "  background: -webkit-linear-gradient(-90deg,#676767 5%, #676767 100%);\n" +
//                        "\n" +
//                        "  -webkit-border-radius: 100px;\n" +
//                        "  -moz-border-radius: 100px;\n" +
//                        "  border-radius: 100px;\n" +
//                        "\n" +
//                        "  position: relative;\n" +
//                        "\n" +
//                        "  margin: 150px auto 0;\n" +
//                        "  opacity: .5;\n" +
//                        "}\n" +
//                        "\n" +
//                        "#cloud:before, #cloud:after{\n" +
//                        "  content: '';\n" +
//                        "  position:absolute;\n" +
//                        "  background: #676767;\n" +
//                        "  z-index: -1;\n" +
//                        "}\n" +
//                        "\n" +
//                        "#cloud:after{\n" +
//                        "  width: 100px;\n" +
//                        "  height: 100px;\n" +
//                        "  top: -50px;\n" +
//                        "  left:50px;\n" +
//                        "\n" +
//                        "  -webkit-border-radius: 100px;\n" +
//                        "  -moz-border-radius: 100px;\n" +
//                        "  border-radius: 100px;\n" +
//                        "}\n" +
//                        "\n" +
//                        "#cloud:before{\n" +
//                        "  width: 120px;\n" +
//                        "  height: 120px;\n" +
//                        "  top: -70px;\n" +
//                        "  right: 50px;\n" +
//                        "\n" +
//                        "  -webkit-border-radius: 200px;\n" +
//                        "  -moz-border-radius: 200px;\n" +
//                        "  border-radius: 200px;\n" +
//                        "}\n" +
//                        "\n" +
//                        ".shadow {\n" +
//                        "  width: 300px;\n" +
//                        "  position: absolute;\n" +
//                        "  bottom: -10px;\n" +
//                        "  background: black;\n" +
//                        "  z-index: -1;\n" +
//                        "\n" +
//                        "  -webkit-box-shadow: 0 0 25px 8px rgba(0,0,0,0.4);\n" +
//                        "  -moz-box-shadow: 0 0 25px 8px rgba(0,0,0,0.4);\n" +
//                        "  box-shadow: 0 0 25px 8px rgba(0,0,0,0.4);\n" +
//                        "\n" +
//                        "  -webkit-border-radius: 50%;\n" +
//                        "  -moz-border-radius: 50%;\n" +
//                        "  border-radius: 50%;\n" +
//                        "\n" +
//                        "}\n" +
//                        "\n" +
//                        "h2 {\n" +
//                        "  color: #fff;\n" +
//                        "  font-size: 20px;\n" +
//                        "  padding-top: 15px;\n" +
//                        "  text-align: center;\n" +
//                        "  margin: 5px auto;\n" +
//                        "}\n" +
//                        "\n" +
//                        "h4 {\n" +
//                        "  color: #fff;\n" +
//                        "  font-size: 12px;\n" +
//                        "  margin: 0 auto;\n" +
//                        "  padding: 0;\n" +
//                        "  text-align: center;\n" +
//                        "}\n" +
//                        "\n" +
//                        " </style>\n" +
//                        "\n" +
//                        "<body>    \n" +
//                        "<div id=\"cloud\"> <h2>No Connection :(</h2>\n" +
//                        "<h4>Check your WiFi or Mobile Internet!</h4>\n" +
//                        "<span class=\"shadow\"></span></div>\n" +
//                        "\n" +
//                        "</body>\n" +
//                        "</html>\n";

//                webView.getSettings().setLoadWithOverviewMode(true);
//                webView.getSettings().setUseWideViewPort(true);
//                webView.setVisibility(View.GONE);
//                webView.reload();
//                webView.setVisibility(View.VISIBLE);
//                webView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                webView.loadUrl("file:///android_asset/error.html");

//                webView.loadDataWithBaseURL(null,myText,"text/html","utf-8",null);

            }

            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                swipe.setRefreshing(false);
            }

        });

    }

    @Override
    public void onBackPressed(){

        if (webView.canGoBack()){
            webView.goBack();
        }else {
            finish();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}


