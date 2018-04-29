package com.route.finder.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.route.finder.service.RouteFinderService;

@RestController
@RequestMapping(value="/")
public class RouteFinderController {

	
	@Autowired
	private RouteFinderService routeFinderServiceImpl;
	
    @RequestMapping(value = "/connected",
            method = RequestMethod.GET,
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
	public String getConnectedStatus(@RequestParam("origin") String origin, @RequestParam("destination") String destination) {
		return routeFinderServiceImpl.getConnectedStatus(origin, destination);
	}
	
}
