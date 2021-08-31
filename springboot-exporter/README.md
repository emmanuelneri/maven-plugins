### springboot-exporter

----

Export spring information from your project, like controllers, with `springboot-exporter` plugin.

####  Using plugin

1. add plugin in pom.xml;
```
<build>
		<plugins>
            ...
			<plugin>
				<groupId>br.com.emmanuelneri</groupId>
				<artifactId>springboot-exporter</artifactId>
				<version>1.1</version>
			</plugin>
		</plugins>
	</build>
```

2. run `mvn br.com.emmanuelneri:springboot-exporter:1.1:export` inside project dir;
3. get file in `{projectDir}/target/classes/springboot-info.json`.
```
{
  "name" : "demo",
  "version" : "0.0.1-SNAPSHOT",
  "mainClass" : "com.example.demo.DemoApplication",
  "controllers" : [ {
    "name" : "com.example.demo.core.CoreController",
    "mapping" : [ "/core" ]
  }, {
    "name" : "com.example.demo.controller.RestSample",
    "mapping" : [ "/rest-sample" ]
  }, {
    "name" : "com.example.demo.controller.Sample",
    "mapping" : [ "/sample" ]
  } ]
}
```