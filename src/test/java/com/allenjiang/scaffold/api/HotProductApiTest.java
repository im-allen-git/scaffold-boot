package com.allenjiang.scaffold.api;

import com.allenjiang.scaffold.config.WebSiteEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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
public class HotProductApiTest {


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void refreshJsonTest() throws Exception {
        String hotClassId = "1";
        String webSite = String.valueOf(WebSiteEnum.IMPORTX.getCode());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/hotProduct/refreshJson").param("hotClassId", hotClassId).param("webSite", webSite)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        String rs = mvcResult.getResponse().getContentAsString();

        System.err.println(rs);
        //.andExpect(MockMvcResultMatchers.content().string("Hello Tom!"))
        //.andDo(MockMvcResultHandlers.print());
    }

}
