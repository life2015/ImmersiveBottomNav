package com.retrox.immersivebottomnav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.scrollView
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scrollView {
            verticalLayout {
                textView {
                    text = "目前仅支持网易云音乐，测试版本6.4.0\n" +
                            "太极请把网易云添加到太极空间。\n" +
                            "记得重启网易云/重启系统。\n" +
                            "这是一个让网易云的底部状态栏能够沉浸的模块，在Android Q上开启底部横条会有类似iPhone X的效果"
                }
            }
        }
    }
}
