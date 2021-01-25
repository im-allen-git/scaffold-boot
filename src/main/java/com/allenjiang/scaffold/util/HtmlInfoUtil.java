package com.allenjiang.scaffold.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.allenjiang.scaffold.util
 * @date:2021-01-13
 */
@Slf4j
public class HtmlInfoUtil {

    public static boolean getUrlInfoAndSave(String url, String filePath) {
        boolean isSu = false;
        try {
            String htmlCode = circleGetUrl(url);
            if (StringUtils.isNotBlank(htmlCode) && htmlCode.length() > 100) {
                htmlCode = htmlCode.replace("id=\"is_static_flag\" value=\"\"", "id=\"is_static_flag\" value=\"1\"");

                MakeHtml.writeHtml(filePath, htmlCode, "YES");
                isSu = true;
            } else {
                System.err.println("getUrlInfoAndSave,url[{" + url + "}],filePath[{" + filePath + "}],htmlCode null");
                log.warn("getUrlInfoAndSave,url[{}],filePath[{}],htmlCode null", url, filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getUrlInfoAndSave,url[{}],filePath[{}],error:", url, filePath, e);
        }
        return isSu;
    }


    private static String circleGetUrl(String url) {
        int count = 0;
        String htmlCode = null;
        while (null == htmlCode && count < 4) {
            try {
                htmlCode = MakeHtml.getHtmlCode(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (null == htmlCode) {
                count++;
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return htmlCode;
    }
}
