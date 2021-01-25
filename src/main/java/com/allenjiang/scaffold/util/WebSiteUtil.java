package com.allenjiang.scaffold.util;

import com.allenjiang.scaffold.config.WebSiteConfig;
import com.allenjiang.scaffold.config.WebSiteEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.allenjiang.scaffold.util
 * @date:2021-01-13
 */
@Component
public class WebSiteUtil {

    @Autowired
    private WebSiteConfig siteConfig;

    private static volatile boolean isUpdate = false;

    public String hotRefreshUrl;

    /**
     * 更新网站路径
     *
     * @param flag : 0 不强制更新, 1 强制更新
     */
    public synchronized void initWebSiteEnum(int flag) {
        if (!isUpdate || flag == 1) {
            // 热卖区更新
            WebSiteEnum.IMPORTX.setHotFilePath(siteConfig.importHotPath);
            WebSiteEnum.KIDS.setHotFilePath(siteConfig.kidsHotPath);
            WebSiteEnum.PETS.setHotFilePath(siteConfig.petsHotPath);

            //热卖区刷新接口
            hotRefreshUrl = siteConfig.hotRefreshUrl;

            // 产品单页更新
            WebSiteEnum.IMPORTX.setSingleProductPath(siteConfig.importSingleProductPath);
            WebSiteEnum.IMPORTX.setSingleProductPath_m(siteConfig.importSingleProductPath_m);

            WebSiteEnum.KIDS.setSingleProductPath(siteConfig.kidsSingleProductPath);
            WebSiteEnum.KIDS.setSingleProductPath_m(siteConfig.kidsSingleProductPath_m);

            WebSiteEnum.PETS.setSingleProductPath(siteConfig.petsSingleProductPath);
            WebSiteEnum.PETS.setSingleProductPath_m(siteConfig.petsSingleProductPath_m);



            // 关键词更新
            WebSiteEnum.IMPORTX.setKeywordHtmlPath(siteConfig.importKeywordHtmlPath);
            WebSiteEnum.KIDS.setKeywordHtmlPath(siteConfig.kidsKeywordHtmlPath);
            WebSiteEnum.PETS.setKeywordHtmlPath(siteConfig.petsKeywordHtmlPath);

            // 搜索更新
            WebSiteEnum.IMPORTX.setSearchProductPath(siteConfig.importSearchProductPath);
            WebSiteEnum.KIDS.setSearchProductPath(siteConfig.importSearchProductPath);
            WebSiteEnum.PETS.setSearchProductPath(siteConfig.importSearchProductPath);

            isUpdate = true;
        }
    }
}
