package ir.tapsell.kotlinsample.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.tapsell.sdk.nativeads.TapsellNativeVideoAd
import ir.tapsell.sdk.nativeads.TapsellNativeVideoAdLoadListener
import ir.tapsell.sdk.nativeads.TapsellNativeVideoAdLoader
import ir.tapsell.kotlinsample.BuildConfig
import ir.tapsell.kotlinsample.R
import kotlinx.android.synthetic.main.list_large_ad_item.view.*
import kotlinx.android.synthetic.main.list_large_item.view.*
import java.util.*

class NativeVideoAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val ads = HashMap<Int, TapsellNativeVideoAd>()
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_AD = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_AD -> {
                NativeBannerListItemAdHolder(
                    context, LayoutInflater
                        .from(context).inflate(R.layout.list_large_ad_item, parent, false)
                )
            }
            VIEW_TYPE_ITEM -> {
                ItemHolder(
                    LayoutInflater
                        .from(context).inflate(R.layout.list_large_item, parent, false)
                )
            }
            else -> throw RuntimeException("Invalid view type in NativeVideoAdapter")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemHolder -> {
                holder.bindView(position)
            }
            is NativeBannerListItemAdHolder -> {
                if (ads.containsKey(position)) {
                    holder.bindView(ads[position]!!)
                } else {
                    holder.clear()
                    TapsellNativeVideoAdLoader.Builder()
                        .setContentViewTemplate(R.layout.tapsell_content_video_ad_template)
                        .setAppInstallationViewTemplate(R.layout.tapsell_app_installation_video_ad_template)
                        .setAutoStartVideoOnScreenEnabled(true)
                        .setFullscreenBtnEnabled(false)
                        .loadAd(context, BuildConfig.TAPSELL_NATIVE_VIDEO,
                            object : TapsellNativeVideoAdLoadListener {

                                override fun onNoNetwork() {
                                }

                                override fun onNoAdAvailable() {
                                }

                                override fun onError(error: String) {
                                }

                                override fun onRequestFilled(tapsellNativeVideoAd: TapsellNativeVideoAd) {
                                    ads[holder.getAdapterPosition()] = tapsellNativeVideoAd
                                    holder.bindView(tapsellNativeVideoAd)
                                }
                            })
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) VIEW_TYPE_AD else VIEW_TYPE_ITEM

    }

    override fun getItemCount(): Int {
        return 10
    }

    class NativeBannerListItemAdHolder(val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(ad: TapsellNativeVideoAd) {
            itemView.adContainer.removeAllViews()
            ad.setCompletionListener { adId ->
                Log.e("Tapsell", "onAdShowFinished: $adId")
            }
            ad.addToParentView(itemView.adContainer)
        }

        fun clear() {
            itemView.adContainer.removeAllViews()
        }
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(index: Int) {
            itemView.tvTitle.text = "Item $index"
        }
    }

}
