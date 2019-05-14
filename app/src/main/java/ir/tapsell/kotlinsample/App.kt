package ir.tapsell.kotlinsample

import androidx.multidex.MultiDexApplication
import ir.tapsell.sdk.Tapsell
import ir.tapsell.sdk.TapsellConfiguration

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        val config = TapsellConfiguration(this)
        config.debugMode = true
        Tapsell.initialize(this, config, BuildConfig.TAPSELL_KEY)
    }
}