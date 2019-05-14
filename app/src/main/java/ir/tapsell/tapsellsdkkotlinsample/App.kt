package ir.tapsell.tapsellsdkkotlinsample

import android.app.Application
import ir.tapsell.sdk.Tapsell
import ir.tapsell.sdk.TapsellConfiguration

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val config = TapsellConfiguration(this)
        config.debugMode = true
        Tapsell.initialize(this, config, BuildConfig.TAPSELL_KEY)
    }
}