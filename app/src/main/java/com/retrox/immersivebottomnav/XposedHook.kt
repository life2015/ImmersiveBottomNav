package com.retrox.aodmod

import android.util.Log
import com.retrox.immersivebottomnav.MainHook
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.IXposedHookZygoteInit
import de.robv.android.xposed.callbacks.XC_LoadPackage

class XposedHook : IXposedHookZygoteInit, IXposedHookLoadPackage {
    init {
        Log.d("ImmersiveBottomNav", "init")
    }
    override fun initZygote(startupParam: IXposedHookZygoteInit.StartupParam) {
        MainHook.initZygote(startupParam)
    }

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        MainHook.handleLoadPackage(lpparam)
    }


}