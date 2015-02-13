package com.repreguica.dndtools;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.io.Serializable;

@EActivity(R.layout.activity_web_view)
@OptionsMenu(R.menu.menu_web_view)
public class WebActivity extends ActionBarActivity {
    @ViewById(R.id.webview)
    WebView webView;
    
    @InstanceState
    public static String url = "file:///android_asset/dndtools.pw/index.html";

    @AfterViews
    public void setUpWebView(){
        webView.loadUrl(url);
        webView.setWebViewClient(new MyWebViewClient());
//        webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
    }
    
    @OptionsItem(R.id.action_back)
    public void actionBack(){
        webView.goBack();
    }
    
    @OptionsItem(R.id.action_foward)
    public void actionFoward(){
        webView.goForward();
    }
    
    @Override
    public void onBackPressed(){
        if(webView.canGoBack()){
            webView.goBack();
        }
        else super.onBackPressed();
    }
    
    @Override
    
    private class MyWebViewClient extends WebViewClient{
        
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            if(url.startsWith("http://dndtools.pw")){
                if(url.contains("lookup")){
                    WebActivity.url = url;
                    return false;
                }
                else{
                    url = url.replaceFirst("http://", "file:///android_asset/");
                    if(! url.substring(url.length() - 2, url.length() -1).equals("/")){
                        url += "index.html";
                    }
                    WebActivity.url = url;
                    view.loadUrl(url);
                    return true;
                }
            }
            WebActivity.url = url;
            return false;
        }
        
        
    }
}
