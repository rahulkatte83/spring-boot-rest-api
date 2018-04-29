package com.route.finder.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.route.finder.dao.CityGraphRepository;

@Service
public class RouteFinderServiceImpl implements RouteFinderService {

	
	@Autowired
	private CityGraphRepository routeFinderRepositoryImpl;
	
	
	public enum YesOrNo {
		
		YES("yes"),
		
		NO("no");
		
		private String yesOrNo;
		
		private YesOrNo(String yesOrNo) {
			this.yesOrNo = yesOrNo;
		}
		
		private String getValue() {
			return this.yesOrNo;
		}
		
	}
	
	@Override
	public String getConnectedStatus(final String origin, final String destination) {
		if(null == origin || null == destination) {
			return YesOrNo.NO.getValue();
		}
		return isConnected(origin.trim(), destination.trim(), routeFinderRepositoryImpl.getCityGraph());
	}
	
	// Perform BFS on city graph
	private String isConnected(final String origin, final String destination, final Map<String, List<String>> cityGraph) {
		Map<String, Boolean> visitedMap = new HashMap<>();
		Queue<String> cityQueue = new LinkedList<>();
		cityQueue.add(origin);
		visitedMap.put(origin, true);
		while(!cityQueue.isEmpty()) {
			String city = cityQueue.poll();	
			List<String> connectedCities = cityGraph.get(city);
			if(null != connectedCities) {
				for(String conntectedCity : connectedCities) {
					if(conntectedCity.equals(destination)) {
						return YesOrNo.YES.getValue();
					}
					if(visitedMap.get(conntectedCity) == null) {
						visitedMap.put(conntectedCity, Boolean.TRUE);
						cityQueue.add(conntectedCity);	
					}
				}				
			}
		}
		return YesOrNo.NO.getValue();
	}

}
