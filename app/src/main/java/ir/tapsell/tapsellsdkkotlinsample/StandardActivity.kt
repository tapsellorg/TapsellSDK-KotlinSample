package ir.tapsell.tapsellsdkkotlinsample

import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import ir.tapsell.sdk.bannerads.TapsellBannerType
import ir.tapsell.sdk.bannerads.TapsellBannerView
import kotlinx.android.synthetic.main.activity_standard.*

class StandardActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_standard)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnStandardBanner.setOnClickListener {
            bannerView.loadAd(this@StandardActivity, BuildConfig.TAPSELL_STANDARD_BANNER,
                    TapsellBannerType.BANNER_320x50)

            inflateDynamicalBannerView()
        }
    }

    private fun inflateDynamicalBannerView() {
        val banner = TapsellBannerView(this, TapsellBannerType.BANNER_320x50,
                BuildConfig.TAPSELL_STANDARD_BANNER)

        val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL

        banner.layoutParams = layoutParams
        adContainer.addView(banner)
    }
}
