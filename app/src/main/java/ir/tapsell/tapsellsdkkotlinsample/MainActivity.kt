package ir.tapsell.tapsellsdkkotlinsample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.tapsell.tapsellsdkkotlinsample.navideAds.NativeActivity
import ir.tapsell.tapsellsdkkotlinsample.prerollAds.PreRollActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnInterstitial.setOnClickListener {
            startActivity(Intent(this@MainActivity, InterstitialActivity::class.java))
        }
        btnNative.setOnClickListener {
            startActivity(Intent(this@MainActivity, NativeActivity::class.java))
        }
        btnStandard.setOnClickListener {
            startActivity(Intent(this@MainActivity, StandardActivity::class.java))
        }
        btnReward.setOnClickListener {
            startActivity(Intent(this@MainActivity, RewardActivity::class.java))
        }
        btnPreRoll.setOnClickListener {
            startActivity(Intent(this@MainActivity, PreRollActivity::class.java))
        }
    }
}
