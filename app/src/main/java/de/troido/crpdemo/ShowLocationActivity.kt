package de.troido.crpdemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView

private const val LOCATION_URL: String = "LOCATION_URL"

fun createStartingIntent(context: Context, url: String): Intent {
    val intent = Intent(context, ShowLocationActivity::class.java)
    intent.putExtra(LOCATION_URL, url)
    return intent
}

class ShowLocationActivity : AppCompatActivity() {
    private val webView: WebView
        get() = findViewById(R.id.web_view) as WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_location)

        val url = intent?.extras?.getString(LOCATION_URL)
        if (url != null) {
            intent.removeExtra(LOCATION_URL)
            webView.loadUrl(url)
        }
    }
}
