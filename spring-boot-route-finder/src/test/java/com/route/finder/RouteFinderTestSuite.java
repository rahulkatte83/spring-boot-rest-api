package com.route.finder;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.route.finder.api.rest.RouteFinderControllerTest;
import com.route.finder.dao.CityGraphRepositoryImplTest;
import com.route.finder.service.RouteFinderServiceImplTest;		

@RunWith(Suite.class)				
@Suite.SuiteClasses({				
  RouteFinderControllerTest.class,
  CityGraphRepositoryImplTest.class,
  RouteFinderServiceImplTest.class
})		
public class RouteFinderTestSuite {

}
