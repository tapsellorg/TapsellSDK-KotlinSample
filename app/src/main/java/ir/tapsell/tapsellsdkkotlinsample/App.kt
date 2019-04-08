package ir.tapsell.tapsellsdkkotlinsample

import android.app.Application
import ir.tapsell.sdk.Tapsell

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Tapsell.initialize(this, BuildConfig.TAPSELL_KEY)
    }
}