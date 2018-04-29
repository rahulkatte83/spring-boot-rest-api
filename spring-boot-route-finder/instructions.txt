1. Runtime environment : Java 1.8, Maven (I have used java 8 stream apis)

2. Test Suite :
			RouteFinderTestSuite.java includes bellow test classes:
				RouteFinderControllerTest.java
				CityGraphRepositoryImplTest.java
				RouteFinderServiceImplTest.java

3. To run the application follow bellow steps
	a. Import the maven project in eclipse or compatible IDE.
	b. Run Application.java file as Java Application.

3. Application supports only HTTP GET method.

5. Exceptional scenarios handled :
	a. In case of http method not supported exception and missing http servlet request parameter 
	   case, api returns "no" as response with HTTP status as OK.
	   	


				
	
