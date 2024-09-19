package com.app.planetclub;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;



public class PlanetClubWebView extends Activity {
    private WebView webView;
    Activity activity;
    private long onRecentBackPressedTime;
    private ProgressDialog progDailog;

    Toolbar toolbar;
    boolean isAdmin = false;
    ImageView backArrow;
    /*Use the below url to load planet club app*/
      String urll = "https://planetclub.in/";
    // String urll = "https://rupeeworld.online/";
    //String urll = "https://brightoak.in/";

    @SuppressLint("NewApi")

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planet_club_webview);
        toolbar = findViewById(R.id.toolbar);
        backArrow = toolbar.findViewById(R.id.navigation_textview);
        backArrow.setOnClickListener(view -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Are you want to close the app ?");
            alert.setPositiveButton("Yes", (dialog, which) -> finish());
            alert.setNegativeButton("No", (dialog, which) -> {

            });


            Dialog dialog = alert.show();
            alert.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
        });
        activity = this;

        progDailog = ProgressDialog.show(activity, "Loading", "Please wait...", true);
        progDailog.setCancelable(false);
        /*Uncomment the below code to load planetclub admin webview app*/

/*        if (isAdmin)
            urll = "https://planetclub.in/admin";*/

        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setDomStorageEnabled(true);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progDailog.show();
                view.loadUrl(url);

                return true;
            }

            @Override
            public void onPageFinished(WebView view, final String url) {
                progDailog.dismiss();

            }


        });

        webView.loadUrl(urll);

    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - onRecentBackPressedTime > 2000) {
            onRecentBackPressedTime = System.currentTimeMillis();
            Toast.makeText(this, getResources().getString(R.string.Please_press_BACK_again_to_exit), Toast.LENGTH_SHORT).show();
            return;
        }
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
        finish();


    }
}
