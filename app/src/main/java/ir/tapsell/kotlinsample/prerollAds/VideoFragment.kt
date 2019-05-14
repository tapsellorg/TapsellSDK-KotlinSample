package ir.tapsell.kotlinsample.prerollAds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.tapsell.sdk.vast.TapsellVast
import ir.tapsell.kotlinsample.BuildConfig
import ir.tapsell.kotlinsample.R
import kotlinx.android.synthetic.main.fragment_video.*

class VideoFragment : Fragment() {

    private var videoPlayerController: VideoPlayerController? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_video, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
    }

    private fun initView(rootView: View) {
        val mVideoPlayerWithAdPlayback =
                rootView.findViewById(R.id.videoPlayerWithAdPlayback) as VideoPlayerWithAdPlayback

        videoPlayerController = VideoPlayerController(this.activity, mVideoPlayerWithAdPlayback,
                playButton, videoExampleLayout, "fa", null)

        // If we've already selected a video, load it now.
        loadVideo()
    }

    private fun loadVideo() {
        if (videoPlayerController == null) {
            return
        }
        videoPlayerController!!.setContentVideo("https://storage.backtory.com/tapsell-server/sdk/VASTContentVideo.mp4")
        videoPlayerController!!.adTagUrl = TapsellVast.getAdTag(BuildConfig.TAPSELL_PRE_ROL_VIDEO,
                TapsellVast.PREROLL_TYPE_BOTH, TapsellVast.VAST_VERSION_3)
    }

    override fun onPause() {
        if (videoPlayerController != null) {
            videoPlayerController!!.pause()
        }
        super.onPause()
    }

    override fun onResume() {
        if (videoPlayerController != null) {
            videoPlayerController!!.resume()
        }
        super.onResume()
    }
}
