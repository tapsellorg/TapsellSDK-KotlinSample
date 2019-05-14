package ir.tapsell.kotlinsample.prerollAds

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import ir.tapsell.kotlinsample.R
import kotlinx.android.synthetic.main.activity_pre_roll.*

class PreRollActivity : AppCompatActivity() {

    private val VIDEO_EXAMPLE_FRAGMENT_TAG = "video_example_fragment_tag"

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
        setContentView(R.layout.activity_pre_roll)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnPreRoll.setOnClickListener {
            initView()
        }
    }

    private fun initView() {
        if (supportFragmentManager.findFragmentByTag(VIDEO_EXAMPLE_FRAGMENT_TAG) == null) {
            supportFragmentManager.beginTransaction().add(R.id.adContainer, VideoFragment(),
                    VIDEO_EXAMPLE_FRAGMENT_TAG).commit()
        }
    }
}
