package com.allenjiang.scaffold.api;

import com.alibaba.fastjson.JSONObject;
import com.allenjiang.scaffold.config.WebSiteEnum;
import com.allenjiang.scaffold.pojo.CommonResult;
import com.allenjiang.scaffold.pojo.HotProduct;
import com.allenjiang.scaffold.pojo.SingleProduct;
import com.allenjiang.scaffold.util.HtmlInfoUtil;
import com.allenjiang.scaffold.util.MakeHtml;
import com.allenjiang.scaffold.util.OkHttpsUtil;
import com.allenjiang.scaffold.util.WebSiteUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: 产品单页接口
 * @date:2021-01-13
 */
@RestController
@Slf4j
@RequestMapping("/singleProduct")
public class SingleProductApi {

    private static final String B2B_PRODUCT_URL = "/product/getProductDetails?pid=%s&markname=&catid1=%s&catid2=%s&isStatic=1";
    private static final String B2C_PRODUCT_URL = "/product/getProductDetailsB2C?pid=%s&markname=&catid1=%s&catid2=%s&isStatic=1";

    @Autowired
    private WebSiteUtil webSiteUtil;

    @CrossOrigin("*")
    @RequestMapping("/genFileWithPidList")
    public CommonResult genFileWithPidList(SingleProduct singleProduct) {

        Assert.notNull(singleProduct, "singleProduct null");
        Assert.notNull(singleProduct.getBcFlag(), "BcFlag null");
        Assert.isTrue(StringUtils.isNotBlank(singleProduct.getPidList()), "PidList is null");

        WebSiteEnum anEnum = WebSiteEnum.getSiteByCode(singleProduct.getWebSite());
        if (anEnum == null) {
            return CommonResult.failed("获取网站失败,当前:" + singleProduct.getWebSite());
        }
        try {
            webSiteUtil.initWebSiteEnum(1);
            String[] splitList = singleProduct.getPidList().split(",");
            Arrays.stream(splitList).forEach(e -> getJsonData(anEnum, e, String.valueOf(singleProduct.getBcFlag())));
            return CommonResult.success("", null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("genJsonByPidList,singleProduct[{}],error:", singleProduct, e);
            return CommonResult.failed(e.getMessage());
        }
    }


    private boolean getJsonData(WebSiteEnum ws, String pid, String bcFlag) {
        Map<String, String> rsMap = new HashMap<>();

        rsMap.put("pid", pid);
        rsMap.put("bcFlag", bcFlag);

        String jsonUrl;
        String jsonPath;


        // PC优先获取json数据，MB的直接thymeleaf

        jsonUrl = ws.getUrl() + "/product/1" + pid + ".json";
        jsonPath = ws.getSingleProductPath() + "1" + pid + ".json";

        rsMap.put("jsonUrl", jsonUrl);
        rsMap.put("jsonSavePath", jsonPath);


        boolean isSu;
        int saveCount = 0;
        JSONObject jsonObject = circleGetProduct(jsonUrl);
        if (null == jsonObject || !jsonObject.containsKey("goodsBean")
                || jsonObject.getJSONObject("goodsBean").getIntValue("valid") == 0) {
            rsMap.put("code", "500");
            rsMap.put("msg", "jsonObject null or product valid=0");
            System.err.println("pid:" + pid + ",rs:" + JSONObject.toJSONString(rsMap));
            return false;
        }

        // 保存json数据
        MakeHtml.writeHtml(jsonPath, jsonObject.toJSONString(), "YES");

        // 获取bean数据
        JSONObject gd = jsonObject.getJSONObject("goodsBean");
        String pUrl = gd.getString("pUrl");

        String catid2 = gd.getString("catid4");
        if (StringUtils.isBlank(catid2)) {
            catid2 = "0";
        }
        rsMap.put("catid2", catid2);
        String catid1 = gd.getString("catid3");
        if (StringUtils.isBlank(catid1)) {
            catid1 = "0";
        }
        rsMap.put("catid1", catid1);

        rsMap.put("bcFlag", bcFlag);
        rsMap.put("pUrl", pUrl);
        // 获取相关的html的url和路径
        genUrlAndPath(ws, rsMap);

        // 获取html信息并且保存
        isSu = HtmlInfoUtil.getUrlInfoAndSave(rsMap.get("pcHtmlUrl"), rsMap.get("pcHtmlPath"));
        rsMap.put("pcSave", String.valueOf(isSu));
        if (isSu) {
            saveCount++;
        } else {
            System.err.println(rsMap.get("pcHtmlUrl") + ";" + rsMap.get("pcHtmlPath") + ",-------error");
        }

        isSu = HtmlInfoUtil.getUrlInfoAndSave(rsMap.get("mbHtmlUrl"), rsMap.get("mbHtmlPath"));
        rsMap.put("mbSave", String.valueOf(isSu));
        if (isSu) {
            saveCount++;
        } else {
            System.err.println(rsMap.get("mbHtmlUrl") + ";" + rsMap.get("mbHtmlPath") + ",-------error");
        }
        rsMap.put("saveCount", String.valueOf(saveCount));
        if (saveCount < 2) {
            System.err.println("pid:" + pid + ",rs:" + JSONObject.toJSONString(rsMap));
        }
        return saveCount >= 2;
    }


    /**
     * 获取相关的html的url和路径
     *
     * @param ws
     * @param rsMap
     */
    private void genUrlAndPath(WebSiteEnum ws, Map<String, String> rsMap) {

        String pcHtmlUrl;
        String pcHtmlPath;
        String mbHtmlUrl;
        String mbHtmlPath;


        String pUrl = rsMap.get("pUrl");

        String rpStr = "-0-";
        if ("8".equals(rsMap.get("bcFlag"))) {
            //B2C 商品
            //PC
            String cmCatid1 = "-" + rsMap.get("catid1") + "-";
            String cmCatid2 = "-" + rsMap.get("catid2") + "-";

            String endUrl = pUrl.substring(pUrl.lastIndexOf("/") + 1).replace(cmCatid1, rpStr).replace(cmCatid2, rpStr);
            pcHtmlPath = ws.getSingleProductPath() + endUrl;
            pcHtmlUrl = (ws.getUrl() + pUrl).replace("/goodsinfo/", "/productbc/");
            //移动端
            mbHtmlUrl = ws.getUrl() + String.format(B2C_PRODUCT_URL, "1" + rsMap.get("pid"), rsMap.get("catid1"), rsMap.get("catid2"));
            mbHtmlPath = ws.getSingleProductPath_m() + endUrl;
            rsMap.put("mbHtmlUrl", mbHtmlUrl);
            rsMap.put("mbHtmlPath", mbHtmlPath);
        } else {
            //B2B 商品
            //PC
            pcHtmlPath = ws.getSingleProductPath() + pUrl.substring(pUrl.lastIndexOf("/") + 1);
            pcHtmlUrl = ws.getUrl() + pUrl;
            //移动端
            mbHtmlUrl = ws.getUrl() + String.format(B2B_PRODUCT_URL, "1" + rsMap.get("pid"), rsMap.get("catid1"), rsMap.get("catid2"));
            mbHtmlPath = ws.getSingleProductPath_m() + pUrl.substring(pUrl.lastIndexOf("/") + 1);
        }
        rsMap.put("pcHtmlUrl", pcHtmlUrl);
        rsMap.put("pcHtmlPath", pcHtmlPath);
        rsMap.put("mbHtmlUrl", mbHtmlUrl);
        rsMap.put("mbHtmlPath", mbHtmlPath);
    }

    private JSONObject circleGetProduct(String url) {
        int count = 0;
        JSONObject jsonObject = null;
        while (null == jsonObject && count < 4) {
            try {
                jsonObject = OkHttpsUtil.callUrlByGet(url);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(url + ",error:", e);
            }

            if (null == jsonObject) {
                count++;
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonObject;
    }

}
