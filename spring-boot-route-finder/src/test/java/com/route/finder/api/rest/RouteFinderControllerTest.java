package com.route.finder.api.rest;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.route.finder.Application;
import com.route.finder.vo.YesOrNo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class RouteFinderControllerTest {
    
    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void initTests() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    
    @Test
    public void testConnected_success() throws Exception{
        mvc.perform(get("/connected?origin=Boston&destination=Newark")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(YesOrNo.YES.getValue()));    	

        mvc.perform(get("/connected?origin=Philadelphia&destination= Newark")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(YesOrNo.YES.getValue()));
        
        mvc.perform(get("/connected?origin=Newark&destination= Philadelphia ")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(YesOrNo.YES.getValue()));
    }

    @Test
    public void testConnected_failure() throws Exception{
        mvc.perform(get("/connected?origin=Boston&destination=dummycity")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(YesOrNo.NO.getValue()));
        
        mvc.perform(get("/connected?origin=&destination=")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(YesOrNo.NO.getValue()));    
    }

    @Test
    public void testConnected_failure_bad_request_invalid_params() throws Exception{
        mvc.perform(get("/connected?origin=Boston")
                .accept(MediaType.APPLICATION_JSON))
        		.andExpect(content().string(YesOrNo.NO.getValue()));
        
        mvc.perform(get("/connected?destination=Boston")
                .accept(MediaType.APPLICATION_JSON))
        		.andExpect(content().string(YesOrNo.NO.getValue()));

    }

    @Test
    public void testConnected_failure_bad_request_http_method_not_supported() throws Exception{
    	
        mvc.perform(post("/connected?destination=Boston")
                .accept(MediaType.APPLICATION_JSON))
        		.andExpect(content().string(YesOrNo.NO.getValue()));

    }
    
}
