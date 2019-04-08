package ir.tapsell.tapsellsdkkotlinsample.navideAds

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ir.tapsell.sdk.AdRequestCallback
import ir.tapsell.sdk.nativeads.TapsellNativeBannerManager
import ir.tapsell.sdk.nativeads.TapsellNativeBannerViewManager
import ir.tapsell.tapsellsdkkotlinsample.BuildConfig
import ir.tapsell.tapsellsdkkotlinsample.R
import kotlinx.android.synthetic.main.activity_native_banner.*

class NativeBannerActivity : AppCompatActivity() {

    private var nativeBannerViewManager: TapsellNativeBannerViewManager? = null

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_banner)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initView()
    }

    private fun initView() {
        btnNativeBanner.setOnClickListener {
            requestNativeBannerAd()
        }

        nativeBannerViewManager = TapsellNativeBannerManager.Builder()
                .setParentView(adContainer)
                .setContentViewTemplate(R.layout.tapsell_content_banner_ad_template)
                .setAppInstallationViewTemplate(R.layout.tapsell_app_installation_banner_ad_template)
                .inflateTemplate(this)
    }

    private fun requestNativeBannerAd() {
        TapsellNativeBannerManager.getAd(this@NativeBannerActivity, BuildConfig.TAPSELL_NATIVE_BANNER,
                object : AdRequestCallback {
                    override fun onResponse(adId: Array<String>) {
                        TapsellNativeBannerManager.bindAd(this@NativeBannerActivity,
                                nativeBannerViewManager,
                                BuildConfig.TAPSELL_NATIVE_BANNER,
                                adId[0])
                    }

                    override fun onFailed(message: String) {
                        Log.e("NativeBannerActivity", "Error: $message")
                    }
                })
    }
}
