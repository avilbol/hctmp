call copy /y D:\development\hc\hallocasa-portal\hallocasamig-endpoint\pom.xml D:\development\hc\hallocasa-portal\hallocasamig-endpoint\pom-local.xml
call copy /y D:\development\hc\hallocasa-portal\hallocasamig-endpoint\pom-server.xml D:\development\hc\hallocasa-portal\hallocasamig-endpoint\pom.xml
SET JAVA_HOME=C:/Program Files/java/jdk1.8.0_60
call mvn -f D:/development/hc/hallocasa-portal/hallocasamig/pom.xml clean package resources:resources -U ^
 -Dwar.output.location=D:/development/hc/hallocasa-environment/prod_target ^
 -Dglassfish.base=/usr/local/hallocasamig/api-server ^
 -Dglassfish.domain=hallocasa-mig -Dglassfish.admin.port=64646 ^
 -Dlogsdir=/usr/local/hallocasamig/logs-prod ^
 -Dglassfish.app.port=64647 ^
 -Dmysql.jdbc=jdbc:mysql://localhost:3306/hallocasaappmig ^
 -Dmysql.pass=htGp64SK ^
 -Dfiles.propertyImages.rootPath=/usr/local/hallocasamig/property-images-prod -Dfiles.userImages.rootPath=/usr/local/hallocasamig/user-images-prod ^
 -Dapidoc.war.location=D:/development/hc/hallocasa-environment/prod_target
call copy /y D:\development\hc\hallocasa-portal\hallocasamig-endpoint\pom-local.xml D:\development\hc\hallocasa-portal\hallocasamig-endpoint\pom.xml
cd D:/development/hc/hallocasa-environment/prod_target
call rename hallocasamig-endpoint-1.1.0.war hallocasamig-endpoint-prod-1.1.0.war
pause