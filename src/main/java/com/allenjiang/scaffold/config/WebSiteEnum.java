package com.allenjiang.scaffold.config;

import java.util.Arrays;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.allenjiang.scaffold
 * @date:2021-01-13
 */
public enum WebSiteEnum {

    /**
     * IMPORT网站
     * //
     */
    //IMPORTX(1, "https://www.import-express.com", "IMPORTX", 'I'),
    IMPORTX(1, "http://192.168.1.29:8087", "IMPORTX", 'I'),
    /**
     * KIDS网站
     */
    //KIDS(2, "https://www.kidsproductwholesale.com", "KIDS", 'K'),
    KIDS(2, "http://192.168.1.29:8087", "KIDS", 'K'),
    /**
     * PETS网站
     */
    //PETS(4, "https://www.petstoreinc.com", "PETS", 'P');
    PETS(4, "http://192.168.1.29:8087", "PETS", 'P');


    /**
     * 下标
     */
    private int code;
    /**
     * 访问网站
     */
    private String url;

    /**
     * 网站名称
     */
    private String name;

    private Character siteType;


    /**
     * 热卖区json文件存放路径
     */
    private String hotFilePath;

    /**
     * 产品单页html和json文件存放路径
     */
    private String singleProductPath;

    private String singleProductPath_m;


    /**
     * 关键词html文件存放路径
     */
    private String keywordHtmlPath;

    /**
     * 搜索html文件存放路径
     */
    private String searchProductPath;


    WebSiteEnum(int code) {
        this.code = code;
    }

    WebSiteEnum(int code, String url) {
        this.code = code;
        this.url = url;
    }

    WebSiteEnum(int code, String url, String name) {
        this.code = code;
        this.url = url;
        this.name = name;
    }

    WebSiteEnum(int code, String url, String name, Character siteType) {
        this.code = code;
        this.url = url;
        this.name = name;
        this.siteType = siteType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getSiteType() {
        return siteType;
    }

    public void setSiteType(Character siteType) {
        this.siteType = siteType;
    }

    public String getHotFilePath() {
        return hotFilePath;
    }

    public void setHotFilePath(String hotFilePath) {
        this.hotFilePath = hotFilePath;
    }

    public String getSingleProductPath() {
        return singleProductPath;
    }

    public void setSingleProductPath(String singleProductPath) {
        this.singleProductPath = singleProductPath;
    }

    public String getSingleProductPath_m() {
        return singleProductPath_m;
    }

    public void setSingleProductPath_m(String singleProductPath_m) {
        this.singleProductPath_m = singleProductPath_m;
    }



    public String getKeywordHtmlPath() {
        return keywordHtmlPath;
    }

    public void setKeywordHtmlPath(String keywordHtmlPath) {
        this.keywordHtmlPath = keywordHtmlPath;
    }

    public String getSearchProductPath() {
        return searchProductPath;
    }

    public void setSearchProductPath(String searchProductPath) {
        this.searchProductPath = searchProductPath;
    }

    public static WebSiteEnum getSiteByCode(int code) {
        return Arrays.stream(WebSiteEnum.values()).filter(e -> code == e.getCode()).findFirst().orElse(null);
    }
}
