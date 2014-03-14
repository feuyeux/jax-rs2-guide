> Jersey2.x的官方示例(https://github.com/jersey/jersey/tree/master/examples)中，包含5个关于处理JSON格式数据的例子
> 
> 它们分别是
> 
- json-jackson
- json-jettison
- json-moxy
- json-processing-webapp
- json-with-padding

###1. Jackson JAX-RS JSON Provider Example ###
1 概要
> D:\-aries\+3rd-party\jersey\examples\json-jackson
> 
三个例子：
>
>- Jackson JSON provider JacksonJaxbJsonProvider：JsonConfiguration.FEATURE_POJO_MAPPING in the Main class) instead of using JAXB (Object->JAXB->JSON) 
- JSON-based representations: JSON and JSON with padding (JSONP). 
- Jackson and JAXB annotations could be mixed together within a single POJO. 

2 测试

    curl -HAccept:application/json http://localhost:8080/jackson/emptyArrayResource
    curl -HAccept:application/json http://localhost:8080/jackson/nonJaxbResource
    curl -HAccept:application/json http://localhost:8080/jackson/combinedAnnotations

3 分析

	D:\-aries\+3rd-party\jersey\examples\json-jackson\src\main\java\org\glassfish\jersey\examples\jackson>tree /f

    服务器类：
	App.java

	根资源类：
	MyApplication.java
	super(
       JacksonFeature.class
    );

 	JSON解析类：
    MyObjectMapperProvider.java 实现了ContextResolver接口

	3组资源和实体：
    CombinedAnnotationBean.java
    CombinedAnnotationResource.java

    EmptyArrayBean.java
    EmptyArrayResource.java

    NonJaxbBean.java
    NonJaxbBeanResource.java

4 单元测试

	JacksonTest extends JerseyTest
 	config.register(new JacksonFeature()).register(MyObjectMapperProvider.class);

	CombinedAnnotationBean responseMsg = target.path("combinedAnnotations").request("application/json").get(CombinedAnnotationBean.class);
5 依赖
>json-jackson需要引入Jersey的媒体支持包jersey-media-json-jackson，该包底层将调用：
>
- org.codehaus.jackson:jackson-core-asl:1.9.11
- org.codehaus.jackson:jackson-mapper-asl:1.9.11
- org.codehaus.jackson:jackson-jaxrs:1.9.11
- org.codehaus.jackson:jackson-xc:1.9.11
      
	<dependency>
		<groupId>org.glassfish.jersey.media</groupId>
        <artifactId>jersey-media-json-jackson</artifactId>
    </dependency>

###2. Jettison JAX-RS JSON Provider Example
1 概要
>D:\-aries\+3rd-party\jersey\examples\json-jettison
>
JAXB based resources with Jettison JSON provider

2 测试

	curl -HAccept:application/json http://localhost:8080/jettison/flights
	curl -HAccept:application/xml http://localhost:8080/jettison/flights
	curl -HAccept:application/json http://localhost:8080/jettison/aircrafts

3 分析

	D:\-aries\+3rd-party\jersey\examples\json-jettison\src\main\java\org\glassfish\jersey\examples\jettison>tree /f

	服务器+根资源类：
	App.java - ResourceConfig
	register(new JettisonFeature())

	XML/JSON解析类：
	JaxbContextResolver.java 实现了ContextResolver接口   
 
    2组资源和实体类：
	AircraftType.java
    AircraftTypeList.java
 
    Flights.java    
    FlightType.java   
	FlightsDataStore.java   	
    ObjectFactory.java
	FlightList.java

4 单元测试

	JsonJettisonTest extends JerseyTest
	config.register(new JettisonFeature()).register(JaxbContextResolver.class);

5 依赖
>
json-jettison需要引入Jersey的媒体支持包jersey-media-json-jettison，该包底层将调用：
>
org.codehaus.jettison:jettison:1.3.3

	<dependency>
		<groupId>org.glassfish.jersey.media</groupId>
		<artifactId>jersey-media-json-jettison</artifactId>
	</dependency>

###3. MOXy JAX-RS JSON Provider Example
1 概要
>D:\-aries\+3rd-party\jersey\examples\json-moxy

2 测试

	http://localhost:9998/jsonmoxy/test

3 分析

	D:\-aries\+3rd-party\jersey\examples\json-moxy\src\main\java\org\glassfish\jersey\examples\jsonmoxy>tree /f

	服务器+根资源类：	  
    App.java - ResourceConfig
	
	JSON解析类：
	JsonMoxyConfigurationContextResolver 实现了ContextResolver接口

	1组资源和实体类：
    JsonResource.java
    TestBean.java

4 单元测试

	JsonResourceTest extends JerseyTest
	final TestBean testBean = target.request(MediaType.APPLICATION_JSON_TYPE).get(TestBean.class);
	final TestBean testBean = target.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(new TestBean("a", 1, 1L), MediaType.APPLICATION_JSON_TYPE), TestBean.class);

5 依赖
>jsonmoxy需要引入Jersey的媒体支持包jersey-media-moxy，该包底层将调用EclipseLink项目的Moxy模块
>
- org.eclipse.persistence:org.eclipse.persistence.moxy:2.5.0
- ==org.eclipse.persistence:org.eclipse.persistence.core:2.5.0
- ====org.eclipse.persistence:org.eclipse.persistence.asm:2.5.0

	<dependency>
		<groupId>org.glassfish.jersey.media</groupId>
		<artifactId>jersey-media-moxy</artifactId>
	</dependency>


###4. JSON-P JAX-RS JSON Provider Example
1 概要
>D:\-aries\+3rd-party\jersey\examples\json-processing-webapp
>
>web应用 演示JSON-P(JSR-353)

2 测试

	curl -H "Content-Type: application/json" -X POST --data '[{"name":"Jersey","site":"http://jersey.java.net"},{"age":33,"phone":"158158158","name":"Foo"},{"name":"JSON-P","site":"http://jsonp.java.net"}]' http://localhost:8080/jsonp-webapp/document/multiple
	curl http://localhost:8080/jsonp-webapp/document
	curl http://localhost:8080/jsonp-webapp/document/1
	curl -H "Content-Type: application/json" -X POST --data '["site"]' http://localhost:8080/jsonp-webapp/document/filter

3 分析
D:\-aries\+3rd-party\jersey\examples\json-processing-webapp>tree /f

	根资源类：
	MyApplication.java
	
	资源类+JSON-P处理类：
	DocumentResourcen.java
	DocumentFilteringResourcen.java

	Hash内存：
	DocumentStorage.java

	└─src
	    ├─main
	    │  ├─java
	    │  │  └─org
	    │  │      └─glassfish
	    │  │          └─jersey
	    │  │              └─examples
	    │  │                  └─jsonp
	    │  │                      │  MyApplication.java
	    │  │                      │
	    │  │                      ├─resource
	    │  │                      │      DocumentFilteringResource.java
	    │  │                      │      DocumentResource.java
	    │  │                      │
	    │  │                      └─service
	    │  │                              DocumentStorage.java
	    │  │
	    │  └─webapp
	    │      └─WEB-INF
	    │              web.xml

web.xml

	<init-param>
		<param-name>javax.ws.rs.Application</param-name>
		<param-value>org.glassfish.jersey.examples.jsonp.MyApplication</param-value>
	</init-param>

DocumentResource.java

	return Json.createArrayBuilder().add(DocumentStorage.store(document)).build();

4 单元测试

	JsonProcessingResourceTest extends JerseyTest
	final Response response = target("document").request(MediaType.APPLICATION_JSON).post(Entity.json(document));

5 依赖
>json-processing-webapp需要引入Jersey的媒体支持包jersey-media-json-processing

![JSON-P](pic/JSON-P.png)

	<dependency>
		<groupId>org.glassfish.jersey.media</groupId>
		<artifactId>jersey-media-json-processing</artifactId>
	</dependency>

###5.JSONP Example
1 概要
>D:\-aries\+3rd-party\jersey\examples\json-with-padding
>
http://en.wikipedia.org/wiki/JSON#JSONP

2 测试

	curl -HAccept:application/json http://localhost:8080/jsonp/changes
	curl -HAccept:application/xml http://localhost:8080/jsonp/changes
	curl -HAccept:application/x-javascript http://localhost:8080/jsonp/changes

3 分析

D:\-aries\+3rd-party\jersey\examples\json-with-padding>tree /f
	
	└─src
	    ├─main
	    │  └─java
	    │      └─org
	    │          └─glassfish
	    │              └─jersey
	    │                  └─examples
	    │                      └─jsonp
	    │                              App.java
	    │                              ChangeListResource.java
	    │                              ChangeRecordBean.java
	    │
	    └─test
	        └─java
	            └─org
	                └─glassfish
	                    └─jersey
	                        └─examples
	                            └─jsonp
	                                    JsonWithPaddingTest.java
ChangeListResource.java

	@Path(App.ROOT_PATH)
	@Produces({"application/x-javascript", "application/json", "application/xml"})
	public class ChangeListResource{
	@GET
	@JSONP(queryParam = JSONP.DEFAULT_QUERY)
	public List<ChangeRecordBean> getChanges(@QueryParam(JSONP.DEFAULT_QUERY) String callback, @QueryParam("type") int type) {
		return changes;
	}

4 单元测试
	
	JsonWithPaddingTest extends JerseyTest
  	GenericType<List<ChangeRecordBean>> genericType = new GenericType<List<ChangeRecordBean>>() {};
	// get the initial representation
	List<ChangeRecordBean> changes = target.path("changes").request("application/json").get(genericType);

5 依赖

	<dependency>
		<groupId>org.glassfish.jersey.media</groupId>
		<artifactId>jersey-media-moxy</artifactId>
	</dependency>
	
	<dependency>
		<groupId>com.sun.xml.bind</groupId>
		<artifactId>jaxb-impl</artifactId>
	</dependency>
