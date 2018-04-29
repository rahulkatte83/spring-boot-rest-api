package com.route.finder.service;


public interface RouteFinderService {

	/**
	 * Returns the connection status of origin city to destination. Two cities are 
	 * connected if one city is reachable for another city directly of through intermediate
	 * connections.
	 * @param origin
	 * @param destination
	 * @return
	 * 			String
	 */
	String getConnectedStatus(final String origin, final String destination);
	
}
