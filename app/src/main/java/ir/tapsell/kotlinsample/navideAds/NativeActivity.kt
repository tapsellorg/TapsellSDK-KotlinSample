package ir.tapsell.kotlinsample.navideAds

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import ir.tapsell.kotlinsample.R
import kotlinx.android.synthetic.main.activity_native.*

class NativeActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_native)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnNativeBannerInActivity.setOnClickListener {
            startActivity(Intent(this@NativeActivity, NativeBannerActivity::class.java))
        }
        btnNativeBannerInList.setOnClickListener {
            startActivity(Intent(this@NativeActivity, NativeBannerInListActivity::class.java))
        }
        btnNativeVideoInActivity.setOnClickListener {
            startActivity(Intent(this@NativeActivity, NativeVideoActivity::class.java))
        }
        btnNativeVideoInList.setOnClickListener {
            startActivity(Intent(this@NativeActivity, NativeVideoInListActivity::class.java))
        }
    }
}
