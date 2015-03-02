#!/bin/sh
mvn clean install -DskipTests
java -jar -Xms2048m -Xmx2048m -XX:NewSize=256m -XX:MaxNewSize=256m -XX:PermSize=256M -XX:MaxPermSize=256m target/spring-boot-4-jersey-1.0.0.jar