package com.allenjiang.scaffold.api;

import com.allenjiang.scaffold.config.WebSiteEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.allenjiang.scaffold.api
 * @date:2021-01-13
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SingleProductApiTest {


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void genFileWithPidListTest() throws Exception {
        String pidList = "623573354418,623001072146";
        String bcFlag = "8";
        String webSite = String.valueOf(WebSiteEnum.IMPORTX.getCode());
        mockMvc.perform(MockMvcRequestBuilders
                .get("/singleProduct/genFileWithPidList")
                .param("pidList", pidList)
                .param("bcFlag", bcFlag)
                .param("webSite", webSite))
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.content().string("Hello Tom!"))
                .andDo(MockMvcResultHandlers.print());
    }

}
