package com.cdeshpande.rss_reader_kotlin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.cdeshpande.rss_reader_kotlin.R

class WebViewActivity : AppCompatActivity() {

    private lateinit var mWebView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.webViewToolbar)

        val title: String? = intent.getStringExtra("Title")
        val url: String? = intent.getStringExtra("URL")

        toolbar.title = title
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        mWebView = findViewById(R.id.newsWebView)
        mWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                url: String?
            ): Boolean {
                if (url != null) {
                    view?.loadUrl(url)
                }
                return true
            }
        }

        if (url != null) {
            mWebView.loadUrl(url)
        }
    }

    override fun onBackPressed() {
        when {
            mWebView.canGoBack() -> mWebView.goBack()
            else -> super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}