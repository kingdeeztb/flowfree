package com.hongtian.flowfree


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class SearchFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 1. 加载布局文件
        return inflater.inflate(R.layout.fragment_search, container, false).apply {
            // 2. 初始化视图
            findViewById<Button>(R.id.buttonAlipaycode)?.setOnClickListener {
                // 处理搜索逻辑
            }
        }
    }
}