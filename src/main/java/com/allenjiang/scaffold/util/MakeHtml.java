package com.allenjiang.scaffold.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class MakeHtml {


    // 存储文件
    public static void writeHtml(String filePath, String info, String flag) {
        PrintWriter pw = null;
        try {
            File writeFile = new File(filePath);
            boolean isExit = writeFile.getParentFile().exists();
            if (!isExit) {
                writeFile.getParentFile().mkdirs();
            }
            isExit = writeFile.exists();
            if (!isExit) {
                writeFile.createNewFile();
            }
            if (!flag.equals("NO")) {
                writeFile.delete();
                writeFile.createNewFile();
            }
//			 2018/09/05 14:24 保存获取的关键词专页静态页乱码问题解决 ly
//			pw = new PrintWriter(new FileOutputStream(filePath, true));
            pw = new PrintWriter(filePath, "UTF-8");
            pw.println(info);
            // 大字段使用完一定要置空处理
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
            }
            info = null;
            System.gc();
        }
    }


    public static String getHtmlCode(String httpUrl) {
        Date before = new Date();
        long star = before.getTime();
        StringBuffer htmlCode = new StringBuffer("");
        InputStream in = null;
        BufferedReader breader = null;
        try {

            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/4.0");
            connection.connect();
            // connection.wait(2000);
            in = connection.getInputStream();
            breader = new BufferedReader(
                    new InputStreamReader(in, "UTF-8"));
            String currentLine;
            while ((currentLine = breader.readLine()) != null) {
                htmlCode.append(currentLine).append("\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (breader != null) {
                try {
                    breader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            Date after = new Date();
            long end = after.getTime();
            long ttime = end - star;
            System.out.println(httpUrl + ",耗时:" + ttime + "毫秒");
        }
        return htmlCode.toString();
    }


}