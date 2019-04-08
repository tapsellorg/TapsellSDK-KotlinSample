package ir.tapsell.tapsellsdkkotlinsample

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ir.tapsell.sdk.*
import ir.tapsell.sdk.TapsellAdRequestOptions.CACHE_TYPE_STREAMED
import kotlinx.android.synthetic.main.activity_interstitial.*

class InterstitialActivity : AppCompatActivity() {

    var ad: TapsellAd? = null

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
        setContentView(R.layout.activity_interstitial)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initView()
    }

    private fun initView() {
        btnInterstitialBanner.setOnClickListener { requestInterstitialBannerAd() }
        btnInterstitialVideo.setOnClickListener { requestInterstitialVideoAd() }
        btnShowAd.setOnClickListener { showAd() }
        btnShowAd.isEnabled = false
    }

    private fun requestInterstitialBannerAd() {
        val options = TapsellAdRequestOptions(CACHE_TYPE_STREAMED)
        Tapsell.requestAd(this@InterstitialActivity, BuildConfig.TAPSELL_INTERSTITIAL_BANNER, options,
                object : TapsellAdRequestListener {
                    override fun onAdAvailable(ad: TapsellAd?) {
                        if (isDestroyed)
                            return

                        this@InterstitialActivity.ad = ad
                        btnShowAd.isEnabled = true
                    }

                    override fun onExpiring(ad: TapsellAd?) {
                        TODO("not implemented")
                    }

                    override fun onNoAdAvailable() {
                        TODO("not implemented")
                    }

                    override fun onError(str: String?) {
                        TODO("not implemented")
                    }

                    override fun onNoNetwork() {
                        TODO("not implemented")
                    }

                })
    }

    private fun requestInterstitialVideoAd() {

    }

    private fun showAd() {
        ad?.let {
            val showOptions = TapsellShowOptions()
            it.show(this@InterstitialActivity, showOptions, object : TapsellAdShowListener {
                override fun onOpened(ad: TapsellAd) {
                    Log.e("MainActivity", "on ad opened")
                }

                override fun onClosed(ad: TapsellAd) {
                    Log.e("MainActivity", "on ad closed")
                }
            })
        }.run {
            Toast.makeText(this@InterstitialActivity,
                    getString(R.string.ad_is_not_available), Toast.LENGTH_LONG).show()
        }

        btnShowAd.isEnabled = false
    }
}