package ir.tapsell.kotlinsample.navideAds

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import ir.tapsell.sdk.nativeads.TapsellNativeVideoAd
import ir.tapsell.sdk.nativeads.TapsellNativeVideoAdLoadListener
import ir.tapsell.sdk.nativeads.TapsellNativeVideoAdLoader
import ir.tapsell.kotlinsample.BuildConfig
import ir.tapsell.kotlinsample.R
import kotlinx.android.synthetic.main.activity_native_video.*

class NativeVideoActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_native_video)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnNativeVideo.setOnClickListener {
            requestNativeVideoAd()
        }
    }

    private fun requestNativeVideoAd() {
        TapsellNativeVideoAdLoader.Builder()
            .setContentViewTemplate(R.layout.tapsell_content_video_ad_template)
            .setAppInstallationViewTemplate(R.layout.tapsell_app_installation_video_ad_template)
            .setAutoStartVideoOnScreenEnabled(false)
            .setFullscreenBtnEnabled(true)
            .setMuteVideoBtnEnabled(true)
            .setMuteVideo(false)
            .loadAd(this, BuildConfig.TAPSELL_NATIVE_VIDEO,
                object : TapsellNativeVideoAdLoadListener {

                    override fun onRequestFilled(tapsellNativeVideoAd: TapsellNativeVideoAd) {
                        tapsellNativeVideoAd.setCompletionListener { adId ->
                            Log.e(
                                "Tapsell",
                                "onAdShowFinished: $adId"
                            )
                        }

                        tapsellNativeVideoAd.setProgressListener { _, progress ->
                            Log.e(
                                "Tapsell",
                                "onNativeAdProgress: $progress"
                            )
                        }

                        tapsellNativeVideoAd.setOnClickListener { }
                        tapsellNativeVideoAd.addToParentView(adParent)
                    }

                    override fun onNoAdAvailable() {
                        TODO("not implemented")
                    }

                    override fun onError(p0: String?) {
                        TODO("not implemented")
                    }

                    override fun onNoNetwork() {
                        TODO("not implemented")
                    }
                })
    }
}
