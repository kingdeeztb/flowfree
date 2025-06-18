package com.hongtian.flowfree

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hongtian.flowfree.ALPermissionManager.RootCommand
import com.hongtian.flowfree.ui.theme.FlowfreeTheme
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader

// 广播接收器代码
class UnlockReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_USER_PRESENT) { // 解锁事件
            val launchIntent = context.packageManager
                .getLaunchIntentForPackage("com.hongtian.flowfree") // 替换包名
            launchIntent?.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(launchIntent)
        }
    }
}

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.flowfree)

        // 找到布局中的Switch控件
        val myopenclncSwitch: Switch = findViewById(R.id.openswitchclnc)
        //按钮button控件
        val myopenclncButton: Button = findViewById(R.id.buttonOpenClnc)
        val mycloseclncButton: Button = findViewById(R.id.buttonCloseClnc)
        val logview: TextView = findViewById(R.id.logview)
        val topview = findViewById<TextView>(R.id.topview)
        val mycleanTencentQQ=findViewById<Button>(R.id.buttonCleanTencentQQ)
        val myAlipayTrip=findViewById<Button>(R.id.buttonAlipayTrip)
        val myAlipaycode=findViewById<Button>(R.id.buttonAlipaycode)

        // 调用RootCommand函数执行命令
        val resultps = RootCommand("uname -a\n")
        val topreslut = "Author:iPanStone@酷安\nDate:2025-05-20 18:00:00\n"

        //+ "✂·····························\n"

        logview.setText(resultps)
        topview.setText(topreslut)


        //为Button控件设置点击状态

        myopenclncButton.setOnClickListener {
            // 在这里处理点击事件
            Toast.makeText(this, "CLNC免流被开启了", Toast.LENGTH_SHORT).show()
            val myclncopenprocess =
                RootCommand("sh /data/CLNC/clnc_magisk/open.sh")// 模块/clnc -p clnc.pid -g 3004 -c ../百度直连.conf
            myclncopenprocess
            println(myclncopenprocess)
            Log.d("TAG", myclncopenprocess)
            logview.setText(myclncopenprocess.toString())
        }
        mycloseclncButton.setOnClickListener {
            // 在这里处理点击事件
            Toast.makeText(this, "CLNC免流被关闭了", Toast.LENGTH_SHORT).show()
            val myclnccloseprocess = RootCommand("sh /data/CLNC/clnc_magisk/close.sh\n")
            println(myclnccloseprocess)
            Log.d("TAG", myclnccloseprocess)
            logview.setText(myclnccloseprocess.toString() + "CLNC免流被关闭了")

        }
        mycleanTencentQQ.setOnClickListener {
            // 在这里处理点击事件
            Toast.makeText(this, "清理QQ后台中...", Toast.LENGTH_SHORT).show()
            val mycleanTencentQQprocess = RootCommand("sh /data/CLNC/cleanTencentQQ.sh\n")
            println(mycleanTencentQQprocess)
            Log.d("TAG", mycleanTencentQQprocess)
            logview.setText(mycleanTencentQQprocess.toString() + "QQ后台清理完毕~")

        }
        myAlipayTrip.setOnClickListener() {
            // 在这里处理点击事件
            Toast.makeText(this, "清理Alipay后台中...", Toast.LENGTH_SHORT).show()
            val mycleanTencentQQprocess = RootCommand("sh /data/CLNC/cleanTencentQQ.sh\n")
            println(mycleanTencentQQprocess)
            Log.d("TAG", mycleanTencentQQprocess)
            logview.setText(mycleanTencentQQprocess.toString() + "QQ后台清理完毕~")

            // 打开支付宝出行界面
            // 打开支付宝出行界面
            try {
                val uri = Uri.parse("alipays://platformapi/startapp?appId=200011235")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "打开支付宝出行失败，请检查是否安装支付宝", Toast.LENGTH_SHORT).show()
                // 备用方案：打开支付宝主界面
                try {
                    val intent = packageManager.getLaunchIntentForPackage("com.eg.android.AlipayGphone")
                    startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(this, "无法打开支付宝", Toast.LENGTH_SHORT).show()
                }
            }

        }

        myAlipaycode.setOnClickListener() {
            // 在这里处理点击事件
            Toast.makeText(this, "清理Alipay后台中...", Toast.LENGTH_SHORT).show()
            val mycleanTencentQQprocess = RootCommand("sh /data/CLNC/cleanTencentQQ.sh\n")
            println(mycleanTencentQQprocess)
            Log.d("TAG", mycleanTencentQQprocess)
            logview.setText(mycleanTencentQQprocess.toString() + "QQ后台清理完毕~")

            // 打开支付宝出行界面
            // 打开支付宝出行界面
            try {
                val uri = Uri.parse("alipays://platformapi/startapp?appId=20000056")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "打开支付宝付款码失败，请检查是否安装支付宝", Toast.LENGTH_SHORT).show()
                // 备用方案：打开支付宝主界面
                try {
                    val intent = packageManager.getLaunchIntentForPackage("com.eg.android.AlipayGphone")
                    startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(this, "无法打开支付宝", Toast.LENGTH_SHORT).show()
                }
            }

        }

        //为Switch控件设置状态改变监听器
        myopenclncSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            // 在这里处理状态改变事件
            if (isChecked) {
                // Switch被打开
                println("Switch is enableView")
                myopenclncSwitch.text = "显示CLNC目录文件"
                val myclncopenprocess = RootCommand("ls /data/adb/modules/CLNC\n")
                // 将命令执行结果显示在logview中
                logview.setText(myclncopenprocess.toString())
            } else {
                // Switch被关闭
                println("Switch is DisableView")
                myopenclncSwitch.text = "不显示CLNC目录文件"
                var myclnccloseprocess = RootCommand("clear && uname -a \n")
                logview.setText(myclnccloseprocess.toString())

            }
        }

//        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
//        bottomNav.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.home -> replaceFragment(HomeFragment())
//                R.id.dashboard -> replaceFragment(DashboardFragment())
//                R.id.notifications -> replaceFragment(NotificationsFragment())
//                else -> false
//            }
//            true
//        }
//
//        fun replaceFragment(fragment: Fragment): Boolean {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fragment_container, fragment)
//                .commit()
//            return true
//        }


    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FlowfreeTheme {
        Greeting("Android")
    }
}


@RequiresApi(Build.VERSION_CODES.N)

fun executeCommand(command: String): String? {
    try {
        // 执行外部命令
        val process = Runtime.getRuntime().exec(command)

        // 读取命令的输出
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        val output = StringBuilder()
        reader.use {
            it.lines().forEach { line ->
                output.appendln(line)
            }
        }
        // 等待命令执行完成
        process.waitFor()
        // 返回命令的执行结果
        return output.toString()
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}

