package com.retrox.immersivebottomnav.hooks

import android.app.Activity
import android.graphics.Color
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.view.children
import com.retrox.immersivebottomnav.MainHook
import com.retrox.immersivebottomnav.extension.XFindViewById
import com.retrox.immersivebottomnav.extension.navigationBarHeight
import com.retrox.immersivebottomnav.extension.setAndroidQImmersive
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage
import org.jetbrains.anko.*

object NetEaseQHook : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (lpparam.packageName != "com.netease.cloudmusic") return

        val clazz = XposedHelpers.findClass("android.app.Activity", lpparam.classLoader)
        XposedHelpers.findAndHookMethod(clazz, "onResume", object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                val activity = param.thisObject as Activity

//                activity.window.statusBarColor = Color.WHITE
                activity.setAndroidQImmersive()
                MainHook.logD("Hook Netease immersive")

                val relative = activity.window.decorView.XFindViewById("re")
                if (relative is RelativeLayout) {
                    val parentFrameLayout  = relative.parent as FrameLayout

                    val children = relative.children.toCollection(mutableListOf())
                    relative.removeAllViews()
                    parentFrameLayout.removeView(relative)
                    parentFrameLayout.relativeLayout {
                        backgroundColor = Color.WHITE
                        children.forEach {
                            this.addView(it)
                        }
                        bottomPadding = activity.navigationBarHeight
                        topPadding = dip(4)
                        layoutParams = FrameLayout.LayoutParams(matchParent, activity.navigationBarHeight +  dip(50)).apply {
                            gravity = Gravity.BOTTOM
                        }
                    }
                }

                val linerLayout2 = activity.window.decorView.XFindViewById("td")
                if (linerLayout2 is LinearLayout) {
                    linerLayout2.apply {
                        bottomPadding = activity.navigationBarHeight
                    }
                }

//                val statusBarBack = activity.window.findViewById<View>(android.R.id.statusBarBackground)
//                statusBarBack.backgroundColor = Color.WHITE

            }
        })




    }
}