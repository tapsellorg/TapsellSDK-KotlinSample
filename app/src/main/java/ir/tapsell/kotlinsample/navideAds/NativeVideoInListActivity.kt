package ir.tapsell.kotlinsample.navideAds

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.tapsell.kotlinsample.R
import ir.tapsell.kotlinsample.adapter.NativeVideoAdapter
import kotlinx.android.synthetic.main.activity_native_video_in_list.*

class NativeVideoInListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_video_in_list)

        btnNativeVideo.setOnClickListener {
            val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            val nativeVideoAdapter = NativeVideoAdapter(this)
            rvItems.apply {
                layoutManager = linearLayoutManager
                adapter = nativeVideoAdapter
            }
        }
    }
}
