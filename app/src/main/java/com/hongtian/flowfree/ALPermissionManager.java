package com.hongtian.flowfree;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

public class ALPermissionManager {
    /**
     * 应用程序运行命令获取 Root权限，设备必须已破解(获得ROOT权限)
     *
     * @param command 命令：String apkRoot="chmod 777 "+getPackageCodePath(); RootCommand(apkRoot);
     * @return 应用程序是/否获取Root权限
     */

    public static String RootCommand(String command) {
        Process process = null;
        DataOutputStream os = null;
        StringBuilder output;
        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(command + "\n");
            os.writeBytes("exit\n");
            os.flush();

            // 读取命令的输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            // 等待命令执行完成
            process.waitFor();
            reader.close();


            // 返回命令的执行结果
            return output.toString();


        } catch (Exception e) {
            Log.d("*** DEBUG ***", "ROOT REE" + e.getMessage());
            return null;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
            }
        }

    }


    public String executeCommand(String command) {
        try {
            // 执行外部命令
            Process process = Runtime.getRuntime().exec(command);
            // 读取命令的输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            reader.close();
            // 等待命令执行完成
            process.waitFor();
            // 返回命令的执行结果
            return output.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
