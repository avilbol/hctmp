call copy /y C:\development\hc\HalloCasa\hallocasamig-endpoint\pom.xml C:\development\hc\HalloCasa\hallocasamig-endpoint\pom-local.xml
call copy /y C:\development\hc\HalloCasa\hallocasamig-endpoint\pom-server.xml C:\development\hc\HalloCasa\hallocasamig-endpoint\pom.xml
SET JAVA_HOME=C:\Program Files\java\jdk1.8.0_121
call mvn -f C:\development\hc\HalloCasa\hallocasamig\pom.xml clean package resources:resources -U ^
 -Dwar.output.location=C:\development\hc\HalloCasa\hallocasa-environment\prod_target ^
 -Dglassfish.base=/usr/local/hallocasamig/api-server ^
 -Dglassfish.domain=hallocasa-mig -Dglassfish.admin.port=64646 ^
 -Dlogsdir=/usr/local/hallocasamig/logs-prod ^
 -Dglassfish.app.port=64647 ^
 -Dmysql.jdbc=jdbc:mysql://localhost:3306/hallocasaappmig ^
 -Dmysql.pass=htGp64SK ^
 -Dfiles.propertyImages.rootPath=/usr/local/hallocasamig/property-images-prod -Dfiles.userImages.rootPath=/usr/local/hallocasamig/user-images-prod ^
 -Dfiles.propertyImages.miniPath=/usr/local/hallocasamig/property-images-prod/mini -Dfiles.userImages.miniPath=/usr/local/hallocasamig/user-images-prod/mini ^
 -Dapidoc.war.location=D:\development\hc\hallocasa-environment\prod_target
call copy /y C:\development\hc\HalloCasa\hallocasamig-endpoint\pom-local.xml C:\development\hc\HalloCasa\hallocasamig-endpoint\pom.xml
cd D:\development\hc\hallocasa-environment\prod_target
call rename hallocasamig-endpoint-1.1.0.war hallocasamig-endpoint-prod-1.1.0.war
pause