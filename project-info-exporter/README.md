### project-info-exporter

----

Export your project information, like name, version and dependencies, with `project-info-exporter` plugin.

####  Using plugin

1. add plugin in pom.xml;
```
<build>
		<plugins>
            ...
			<plugin>
				<groupId>br.com.emmanuelneri</groupId>
				<artifactId>project-info-exporter</artifactId>
				<version>1.0</version>
			</plugin>
		</plugins>
	</build>
```

2. run `mvn br.com.emmanuelneri:project-info-exporter:1.0-SNAPSHOT:export` inside project dir;
3. get file in `{projectDir}/target/classes/project-info.json`.
```
{
  "name" : "demo",
  "version" : "0.0.1-SNAPSHOT",
  "dependencies" : [ {
    "groupId" : "org.springframework.boot",
    "artifactId" : "spring-boot-starter-actuator",
    "version" : "2.5.4"
  }, {
    "groupId" : "org.springframework.boot",
    "artifactId" : "spring-boot-starter-integration",
    "version" : "2.5.4"
  }, {
    "groupId" : "org.springframework.boot",
    "artifactId" : "spring-boot-starter-web",
    "version" : "2.5.4"
  }, {
    "groupId" : "org.springframework.cloud",
    "artifactId" : "spring-cloud-starter-circuitbreaker-resilience4j",
    "version" : "2.0.2"
  }, {
    "groupId" : "org.springframework.integration",
    "artifactId" : "spring-integration-http",
    "version" : "5.5.3"
  }, {
    "groupId" : "org.springframework.boot",
    "artifactId" : "spring-boot-starter-test",
    "version" : "2.5.4"
  }, {
    "groupId" : "org.springframework.integration",
    "artifactId" : "spring-integration-test",
    "version" : "5.5.3"
  }, {
    "groupId" : "com.google.guava",
    "artifactId" : "guava",
    "version" : "30.1.1-jre"
  } ]
}
```
