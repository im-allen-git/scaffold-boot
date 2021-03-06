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
        //String pidList = "558402887332,561626515417,624783027827,609248064715,601747073381,601819130073,601938620834,580516030929,575080000902,554317452640,563584255508,567118535896";
        //String bcFlag = "8";

        String pidList = "566232642484,41571699899,594965701772,575083012125,571346214678,589593949131,576250953887,558473214921,529785789979,584816508308,556815741781,37925711067";
        String bcFlag = "0";
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
