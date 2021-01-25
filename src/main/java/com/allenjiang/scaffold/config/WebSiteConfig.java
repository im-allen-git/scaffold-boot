package com.allenjiang.scaffold.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSiteConfig {

    //----------------热卖区 begin---------------------
    /**
     * import热卖区的静态化文件路径
     */
    @Value("${hot.path.importx}")
    public String importHotPath;

    /**
     * kids热卖区的静态化文件路径
     */
    @Value("${hot.path.kids}")
    public String kidsHotPath;

    /**
     * pets热卖区的静态化文件路径
     */
    @Value("${hot.path.pets}")
    public String petsHotPath;

    /**
     * 热卖区刷新接口
     */
    @Value("${hot.refresh.url}")
    public String hotRefreshUrl;
    //----------------热卖区 end---------------------


    //----------------产品单页 begin---------------------
    /**
     * import的产品单页静态化文件路径(json和html)
     */
    @Value("${single-product.path.importx}")
    public String importSingleProductPath;
    @Value("${single-product.path.importx-m}")
    public String importSingleProductPath_m;


    /**
     * kids的产品单页静态化文件路径(json和html)
     */
    @Value("${single-product.path.kids}")
    public String kidsSingleProductPath;
    @Value("${single-product.path.kids-m}")
    public String kidsSingleProductPath_m;

    /**
     * pets的产品单页静态化文件路径(json和html)
     */
    @Value("${single-product.path.pets}")
    public String petsSingleProductPath;
    @Value("${single-product.path.pets-m}")
    public String petsSingleProductPath_m;
    //----------------产品单页 end---------------------


    //----------------搜索页 begin---------------------
    /**
     * importx的搜索页静态化文件路径(json和html)
     */
    @Value("${search-product.path.importx}")
    public String importSearchProductPath;

    /**
     * kids的搜索页静态化文件路径(json和html)
     */
    @Value("${search-product.path.kids}")
    public String kidsSearchProductPath;

    /**
     * pets的搜索页静态化文件路径(json和html)
     */
    @Value("${search-product.path.pets}")
    public String petsSearchProductPath;
    //----------------搜索页 end---------------------


    //----------------关键词专页 begin---------------------
    /**
     * importx关键词专页html的路径
     */
    @Value("${keyword-html.path.importx}")
    public String importKeywordHtmlPath;

    /**
     * kids关键词专页html的路径
     */
    @Value("${keyword-html.path.kids}")
    public String kidsKeywordHtmlPath;

    /**
     * 关键词专页html的路径
     */
    @Value("${keyword-html.path.pets}")
    public String petsKeywordHtmlPath;

    //----------------关键词专页 end---------------------


}
