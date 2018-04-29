package com.route.finder.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;


@Repository
public class CityGraphRepositoryImpl implements CityGraphRepository {

	private Map<String, List<String>> cityGraph = null;

	@Autowired
	private ResourceLoader resourceLoader;

	@Override
	public List<String> getConnectedCities(String city) {
		return cityGraph.get(city);
	}

	@Override
	public Map<String, List<String>> getCityGraph() {
		return this.cityGraph;
	}
	
	@PostConstruct
	private void primeCityGraph() throws IOException {
		cityGraph = new HashMap<>();
		BufferedReader reader = readCityGraph();
		reader.lines().collect(Collectors.toList()).forEach(line -> {
			String[] citypair = line.split(",");
			populateRoute(citypair[0].trim(), citypair[1].trim());
			populateRoute(citypair[1].trim(), citypair[0].trim());			
		});
	}

	private BufferedReader readCityGraph() throws IOException {
		Resource resource = resourceLoader.getResource("classpath:city.txt");
		InputStream inputStream = resource.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		return reader;
	}

	private void populateRoute(final String origin, final String destination) {
		if (null == cityGraph.get(origin)) {
			List<String> list = new LinkedList<>();
			list.add(destination);
			cityGraph.put(origin, list);
		} else {
			cityGraph.get(origin).add(destination);
		}
	}

}
