package com.allenjiang.scaffold.api;

import com.allenjiang.scaffold.config.WebSiteEnum;
import com.allenjiang.scaffold.pojo.CommonResult;
import com.allenjiang.scaffold.pojo.HotClassInfo;
import com.allenjiang.scaffold.service.HotProductService;
import com.allenjiang.scaffold.util.HtmlInfoUtil;
import com.allenjiang.scaffold.util.WebSiteUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: 热卖区接口
 * @date:2021-01-13
 */
@RestController
@Slf4j
@RequestMapping("/hotProduct")
public class HotProductApi {

    @Autowired
    private WebSiteUtil webSiteUtil;

    @Autowired
    private HotProductService productService;


    /**
     * 热卖区刷新
     *
     * @param hotClassInfo
     */
    @CrossOrigin("*")
    @RequestMapping("/refreshJson")
    public CommonResult refreshJson(HotClassInfo hotClassInfo) {
        Assert.notNull(hotClassInfo, "hotClassInfo null");
        WebSiteEnum anEnum = WebSiteEnum.getSiteByCode(hotClassInfo.getWebSite());
        if (anEnum == null) {
            return CommonResult.failed("获取网站失败,当前:" + hotClassInfo.getWebSite());
        }
        try {
            webSiteUtil.initWebSiteEnum(1);
            List<HotClassInfo> list = productService.queryAllHotClassInfo();
            int count = 0;
            int total = 0;
            if (null == list || list.size() == 0) {
                return CommonResult.failed("获取分组失败!");
            }
            if (null == hotClassInfo.getHotClassId() || hotClassInfo.getHotClassId() == 0) {
                for (HotClassInfo classInfo : list) {
                    total++;
                    boolean isSu = getJsonData(anEnum, classInfo, hotClassInfo.getFreeFlag());
                    if (isSu) {
                        count++;
                    }
                }
            } else {
                HotClassInfo genInfo = list.stream().filter(e -> hotClassInfo.getHotClassId().equals(e.getId())).findFirst().orElse(null);
                if (null == genInfo) {
                    return CommonResult.failed("匹配分组失败!");
                }
                total = 1;
                boolean isSu = getJsonData(anEnum, hotClassInfo, hotClassInfo.getFreeFlag());
                if (isSu) {
                    count++;
                }
            }
            return CommonResult.success("总数:" + total + ",成功数:" + count, null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("refreshJson hotClassInfo:" + hotClassInfo + ", error:", e);
            return CommonResult.failed(e.getMessage());
        }
    }


    /**
     * 获取json数据并且保存
     *
     * @param ws
     * @param hotClassInfo
     * @param freeFlag
     * @return
     */
    private boolean getJsonData(WebSiteEnum ws, HotClassInfo hotClassInfo, int freeFlag) {
        String url = ws.getUrl() + webSiteUtil.hotRefreshUrl + hotClassInfo.getId() + "&freeFlag=" + freeFlag;
        String filePath = ws.getHotFilePath() + hotClassInfo.getJsonName();

        System.err.println(ws + "," + hotClassInfo);
        System.err.println(url + "," + filePath);
        return HtmlInfoUtil.getUrlInfoAndSave(url, filePath);
    }


}
