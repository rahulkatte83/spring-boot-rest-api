package com.route.finder.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface CityGraphRepository {
	
	/**
	 * Returns cities which are directly connected with input city.
	 * @param city
	 * @return
	 * 			list of cities which are directly reachable from input city
	 */
	public List<String> getConnectedCities(String city);
	
	
	/**
	 * Returns graphical representation of cities in terms of map.
	 * @return
	 * 			Map of list of connected cities
	 */
	public Map<String, List<String>> getCityGraph();
	
}
