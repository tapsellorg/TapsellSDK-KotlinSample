package ir.tapsell.kotlinsample.navideAds

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.tapsell.sdk.AdRequestCallback
import ir.tapsell.sdk.CacheSize
import ir.tapsell.sdk.nativeads.TapsellNativeBannerManager
import ir.tapsell.kotlinsample.BuildConfig
import ir.tapsell.kotlinsample.R
import ir.tapsell.kotlinsample.adapter.NativeBannerAdapter
import ir.tapsell.kotlinsample.model.ItemList
import ir.tapsell.kotlinsample.model.ListItemType
import kotlinx.android.synthetic.main.activity_native_banner_in_list.*

class NativeBannerInListActivity : AppCompatActivity() {

    private val items = ArrayList<ItemList>()

    private lateinit var nativeBannerAdapter: NativeBannerAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var isLoading = false
    private var currentPage = 0
    private val pageSize = 20

    private val STATE_LIST = "STATE_LIST"

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(STATE_LIST, items)

        TapsellNativeBannerManager.onSaveInstanceState(
            this,
            BuildConfig.TAPSELL_NATIVE_BANNER, outState
        )
        super.onSaveInstanceState(outState)
    }

    private fun restoreState(savedInstanceState: Bundle) {
        TapsellNativeBannerManager.onRestoreInstanceState(
            this,
            BuildConfig.TAPSELL_NATIVE_BANNER, savedInstanceState
        )

        items.addAll(savedInstanceState.getSerializable(STATE_LIST) as ArrayList<ItemList>)
        updateList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_banner_in_list)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnNativeBanner.setOnClickListener {
            initAdCache()
            initList()

            if (savedInstanceState != null) {
                restoreState(savedInstanceState)
                return@setOnClickListener
            }
            generateItems(0)
        }
    }

    private fun initAdCache() {
        TapsellNativeBannerManager.createCache(
            this, BuildConfig.TAPSELL_NATIVE_BANNER,
            CacheSize.MEDIUM
        )
    }

    private fun initList() {
        linearLayoutManager = LinearLayoutManager(this@NativeBannerInListActivity)
        nativeBannerAdapter = NativeBannerAdapter(this)
        rvItems.apply {
            layoutManager = linearLayoutManager
            adapter = nativeBannerAdapter
        }

        rvItems.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy < 0) {
                    return
                }

                val visibleItemCount = linearLayoutManager.childCount
                val totalItemCount = linearLayoutManager.itemCount
                val firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()

                if (!isLoading) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount &&
                        firstVisibleItemPosition >= 0 &&
                        totalItemCount >= pageSize
                    ) {
                        generateItems(++currentPage)
                    }
                }
            }
        })
    }

    private fun generateItems(page: Int) {
        for (i in 0 until pageSize) {
            val item = ItemList()
            item.title = "item " + (page * pageSize + i)
            item.listItemType = ListItemType.ITEM
            items.add(item)
        }

        isLoading = false

        updateList()
        getNativeBannerAd()
    }

    private fun updateList() {
        rvItems.post {
            nativeBannerAdapter.updateItem(items)
        }
    }

    private fun getNativeBannerAd() {
        TapsellNativeBannerManager.getAd(this, BuildConfig.TAPSELL_NATIVE_BANNER,
            object : AdRequestCallback {
                override fun onResponse(adsId: Array<String>) {
                    val item = ItemList()

                    item.id = adsId[0]
                    item.listItemType = ListItemType.AD

                    items.add(item)

                    updateList()
                }

                override fun onFailed(message: String) {
                    Log.e("NativeBannerActivity", "Error: $message")
                }
            })
    }
}
