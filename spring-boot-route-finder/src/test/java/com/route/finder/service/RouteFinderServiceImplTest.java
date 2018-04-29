package com.route.finder.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.route.finder.dao.CityGraphRepository;

public class RouteFinderServiceImplTest {

	@InjectMocks
	RouteFinderService routeFinderServiceImpl = new RouteFinderServiceImpl();
	
	@Mock
	CityGraphRepository cityGraphRepositoryImpl;
	
	private Map<String, List<String>> cityGraph;
	
    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        cityGraph = new HashMap<>();
		populateGraph();
    }

	private void populateGraph() {
		populateRoute("Boston", "New York");
		populateRoute("New York", "Boston");
		populateRoute("Philadelphia", "Newark");
		populateRoute("Newark", "Philadelphia");
		populateRoute("Newark", "Boston");
		populateRoute("Boston", "Newark");
		populateRoute("Trenton", "Albany");
		populateRoute("Albany", "Trenton");
	}
    
    @Test
    public void testIsConnected_success() {
    	Mockito.when(cityGraphRepositoryImpl.getCityGraph()).thenReturn(cityGraph);
    	String status = routeFinderServiceImpl.getConnectedStatus("New York", "Boston");
    	Assert.assertEquals("yes", status);
    	
    	status = routeFinderServiceImpl.getConnectedStatus("Newark", "Philadelphia");
    	Assert.assertEquals("yes", status);
    	
    	status = routeFinderServiceImpl.getConnectedStatus("Newark", "Boston");
    	Assert.assertEquals("yes", status);
    	
    	status = routeFinderServiceImpl.getConnectedStatus("Boston", "Philadelphia");
    	Assert.assertEquals("yes", status);

    }
    
    @Test
    public void testIsConnected_failure() {
    	String status = routeFinderServiceImpl.getConnectedStatus("test", "Boston");
    	Assert.assertEquals("no", status);
    	
    	status = routeFinderServiceImpl.getConnectedStatus(null, null);
    	Assert.assertEquals("no", status);
    	
    	status = routeFinderServiceImpl.getConnectedStatus("Philadelphia", "Albany");
    	Assert.assertEquals("no", status);
    }
    
	private void populateRoute(String origin, String destination) {
		if (null == cityGraph.get(origin)) {
			List<String> list = new LinkedList<>();
			list.add(destination);
			cityGraph.put(origin, list);
		} else {
			cityGraph.get(origin).add(destination);
		}
	}
	
}
