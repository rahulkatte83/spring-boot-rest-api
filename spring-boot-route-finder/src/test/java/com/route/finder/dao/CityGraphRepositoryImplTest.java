package com.route.finder.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.hamcrest.CustomMatcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.route.finder.Application;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class CityGraphRepositoryImplTest {

	@Autowired
	private CityGraphRepository cityGraphRepositoryImpl = new CityGraphRepositoryImpl();
	
	private static final String ERROR_MESSAGE = "Failed to load cities";
		
    @Before
    public void initTests() {

    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
    public void testCityGraph() {
    	
    	Map<String, List<String>> graph = cityGraphRepositoryImpl.getCityGraph();
    	Assert.assertNotNull(ERROR_MESSAGE, graph.get("New York"));
    	Assert.assertThat(graph.get("New York"), new CustomMatcher(ERROR_MESSAGE) {
			@Override
			public boolean matches(Object item) {
				return ((List<String>)item).containsAll(Arrays.asList("Boston"));
			}
    	}); 

    	
       	Assert.assertNotNull(ERROR_MESSAGE, graph.get("Newark"));
    	Assert.assertThat(graph.get("Newark"), new CustomMatcher(ERROR_MESSAGE) {
			@Override
			public boolean matches(Object item) {
				return ((List<String>)item).containsAll(Arrays.asList("Boston", "Philadelphia"));
			}
    	}); 

       	Assert.assertNotNull(ERROR_MESSAGE, graph.get("Boston"));
    	Assert.assertThat(graph.get("Boston"), new CustomMatcher(ERROR_MESSAGE) {
			@Override
			public boolean matches(Object item) {
				return ((List<String>)item).containsAll(Arrays.asList("New York", "Newark"));
			}
    	});
    	
    	Assert.assertNotNull(ERROR_MESSAGE, graph.get("Philadelphia"));
    	Assert.assertThat(graph.get("Philadelphia"), new CustomMatcher(ERROR_MESSAGE) {
			@Override
			public boolean matches(Object item) {
				return ((List<String>)item).containsAll(Arrays.asList("Newark"));
			}
    	}); 
    	
    	Assert.assertNotNull(ERROR_MESSAGE, graph.get("Trenton"));
    	Assert.assertThat(graph.get("Trenton"), new CustomMatcher(ERROR_MESSAGE) {
			@Override
			public boolean matches(Object item) {
				return ((List<String>)item).containsAll(Arrays.asList("Albany"));
			}
    	}); 
    	
    	Assert.assertNotNull(ERROR_MESSAGE, graph.get("Albany"));
    	Assert.assertThat(graph.get("Albany"), new CustomMatcher(ERROR_MESSAGE) {
			@Override
			public boolean matches(Object item) {
				return ((List<String>)item).containsAll(Arrays.asList("Trenton"));
			}
    	}); 
  
    }

}
