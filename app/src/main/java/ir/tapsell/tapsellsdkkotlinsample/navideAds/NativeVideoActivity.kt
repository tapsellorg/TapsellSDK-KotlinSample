package ir.tapsell.tapsellsdkkotlinsample.navideAds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import ir.tapsell.tapsellsdkkotlinsample.R

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
    }
}
