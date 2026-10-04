
package com.smartagro.marketplace

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.widget.TextView
import android.widget.FrameLayout

class MainActivity : Activity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = Color.rgb(8, 127, 91)
        window.navigationBarColor = Color.rgb(6, 63, 46)

        try {
            webView = WebView(this)

            webView.setBackgroundColor(Color.rgb(244, 250, 247))

            webView.settings.javaScriptEnabled = true
            webView.settings.domStorageEnabled = true
            webView.settings.allowFileAccess = true

            webView.webViewClient = WebViewClient()
            webView.webChromeClient = WebChromeClient()

            webView.isVerticalScrollBarEnabled = false

            setContentView(webView)

            webView.loadUrl("file:///android_asset/index.html")

        } catch (e: Exception) {
            val errorView = TextView(this)
            errorView.text =
                "App start error:\n${e.javaClass.simpleName}\n${e.message}"
            errorView.textSize = 16f
            errorView.setPadding(24, 24, 24, 24)
            errorView.setTextColor(Color.RED)

            setContentView(errorView)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (::webView.isInitialized && webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        if (::webView.isInitialized) {
            webView.destroy()
        }
        super.onDestroy()
    }
}
