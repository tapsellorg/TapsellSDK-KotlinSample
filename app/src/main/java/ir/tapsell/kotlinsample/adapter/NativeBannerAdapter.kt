package ir.tapsell.kotlinsample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.tapsell.sdk.nativeads.TapsellNativeBannerManager
import ir.tapsell.sdk.nativeads.TapsellNativeBannerViewManager
import ir.tapsell.kotlinsample.BuildConfig
import ir.tapsell.kotlinsample.R
import ir.tapsell.kotlinsample.model.ItemList
import ir.tapsell.kotlinsample.model.ListItemType
import kotlinx.android.synthetic.main.list_ad_item.view.*
import kotlinx.android.synthetic.main.list_item.view.*


class NativeBannerAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: MutableList<ItemList> = ArrayList()
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_AD = 1

    fun updateItem(items: List<ItemList>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_AD -> {
                NativeBannerListItemAdHolder(
                    context, LayoutInflater
                        .from(context).inflate(R.layout.list_ad_item, parent, false)
                )
            }
            VIEW_TYPE_ITEM -> {
                ItemHolder(
                    LayoutInflater
                        .from(context).inflate(R.layout.list_item, parent, false)
                )
            }
            else -> throw RuntimeException("Invalid view type in NativeBannerAdapter")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemHolder -> {
                holder.bindView(items[position])
            }
            is NativeBannerListItemAdHolder -> {
                holder.bindView(items[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].listItemType === ListItemType.ITEM) {
            VIEW_TYPE_ITEM
        } else {
            VIEW_TYPE_AD
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class NativeBannerListItemAdHolder(val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nativeBannerViewManager: TapsellNativeBannerViewManager = TapsellNativeBannerManager.Builder()
            .setParentView(itemView.adContainer)
            .setContentViewTemplate(R.layout.tapsell_content_banner_ad_template)
            .setAppInstallationViewTemplate(R.layout.tapsell_app_installation_banner_ad_template)
            .inflateTemplate(context)

        fun bindView(item: ItemList) {
            TapsellNativeBannerManager.bindAd(
                context, nativeBannerViewManager,
                BuildConfig.TAPSELL_NATIVE_BANNER, item.id
            )
        }
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: ItemList) {
            itemView.tvTitle.text = item.title
        }
    }

}
