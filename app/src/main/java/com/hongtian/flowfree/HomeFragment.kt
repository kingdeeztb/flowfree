package com.hongtian.flowfree

import com.hongtian.flowfree.ALPermissionManager.RootCommand
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hongtian.flowfree.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.fragment_home, container, false).apply {
            // 2. 初始化视图
            binding = FragmentHomeBinding.inflate(inflater, container, false)
            initializeViews()
            return binding.root
        }

    }

    private fun initializeViews() {
        // 初始信息显示
        executeCommandAndShowResult("uname -a")
        binding.topview.text = "Author:iPanStone@酷安\nDate:2025-05-20 18:00:00"

        // 按钮点击事件
        binding.buttonOpenClnc.setOnClickListener {
            showToast(R.string.open_clnc_success)
            executeCommandAndShowResult("sh /data/CLNC/clnc_magisk/open.sh")
        }

        binding.buttonCloseClnc.setOnClickListener {
            showToast(R.string.open_clnc_success)
            executeCommandAndShowResult("sh /data/CLNC/clnc_magisk/close.sh", "CLNC免流被关闭了")
        }

        binding.buttonCleanTencentQQ.setOnClickListener {
            showToast(R.string.open_clnc_success)
            executeCommandAndShowResult("sh /data/CLNC/cleanTencentQQ.sh", "QQ后台清理完毕~")
        }

        binding.buttonAlipayTrip.setOnClickListener {
            showToast(R.string.open_clnc_success)
            executeCommandAndShowResult("sh /data/CLNC/cleanTencentQQ.sh", "QQ后台清理完毕~")
            openAlipay(
                "alipays://platformapi/startapp?appId=200011235",
                "R.string.open_trip_failed"
            )
        }

        binding.buttonAlipaycode.setOnClickListener {
            showToast(R.string.open_clnc_success)
            executeCommandAndShowResult("sh /data/CLNC/cleanTencentQQ.sh", "QQ后台清理完毕~")
            openAlipay("alipays://platformapi/startapp?appId=20000056", "R.string.open_code_failed")
        }

        // Switch状态监听
        binding.openswitchclnc.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.openswitchclnc.text = "R.string.show_clnc_files"
                executeCommandAndShowResult("ls /data/adb/modules/CLNC")
            } else {
                binding.openswitchclnc.text = "R.string.hide_clnc_files"
                executeCommandAndShowResult("clear && uname -a")
            }
        }
    }

    private fun executeCommandAndShowResult(command: String, successMessage: String = "") {
        val result = RootCommand(command)
        Log.d("TAG", result)
        binding.logview.text = "$result$successMessage"
    }

    private fun showToast(messageResId: Int) {
        Toast.makeText(requireContext(), getText(messageResId), Toast.LENGTH_SHORT).show()
    }

    private fun openAlipay(uriString: String, errorMessageResId: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uriString))
            startActivity(intent)
        } catch (e: Exception) {
            Log.e("HomeFragment", "Error opening Alipay", e)
            showToast(R.string.open_clnc_success)
            tryOpenAlipayMain()
        }
    }

    private fun tryOpenAlipayMain() {
        try {
            val intent = requireActivity().packageManager
                .getLaunchIntentForPackage("com.eg.android.AlipayGphone")
            startActivity(intent)
        } catch (e: Exception) {
            showToast(R.string.open_clnc_success)
        }
    }
}