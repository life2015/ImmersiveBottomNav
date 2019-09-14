package com.retrox.immersivebottomnav

import android.util.Log
import com.retrox.immersivebottomnav.hooks.NetEaseQHook
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.IXposedHookZygoteInit
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.callbacks.XC_LoadPackage

object MainHook : IXposedHookZygoteInit, IXposedHookLoadPackage {

    const val TAG = "ImmersiveBottomNav"


    override fun initZygote(startupParam: IXposedHookZygoteInit.StartupParam) {
//        SystemServiceHook.initZygote(startupParam)
    }

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        Log.d("ImmersiveBottomNav", "package: ${lpparam.packageName}")

      NetEaseQHook.handleLoadPackage(lpparam)

    }

    private const val LOG_FORMAT = "[ImmersiveBottomNav] %1\$s %2\$s: %3\$s"

    fun logE(msg: String, tag: String = TAG, t: Throwable? = null) {
        XposedBridge.log(String.format(LOG_FORMAT, "[ERROR]", tag, msg))
        t?.let { XposedBridge.log(it) }
//        Log.e(tag, msg, t)
    }

    fun logW(msg: String, tag: String = TAG) {
        XposedBridge.log(String.format(LOG_FORMAT, "[WARNING]", tag, msg))
//        Log.w(tag, msg)

    }

    fun logI(msg: String, tag: String = TAG) {
        XposedBridge.log(String.format(LOG_FORMAT, "[INFO]", tag, msg))
//        Log.i(tag, msg)

    }

    fun logD(msg: String, tag: String = TAG) {
        val message = String.format(LOG_FORMAT, "[DEBUG]", tag, msg)
        XposedBridge.log(message)
//        Log.d(tag, msg)
    }

}