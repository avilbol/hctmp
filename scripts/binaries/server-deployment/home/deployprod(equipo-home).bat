call copy /y D:\development\hallocasa-portal\HalloCasa\hallocasamig-endpoint\pom.xml D:\development\hallocasa-portal\HalloCasa\hallocasamig-endpoint\pom-local.xml
call copy /y D:\development\hallocasa-portal\HalloCasa\hallocasamig-endpoint\pom-server.xml D:\development\hallocasa-portal\HalloCasa\hallocasamig-endpoint\pom.xml
SET JAVA_HOME=C:/Program Files/java/jdk1.8.0_73
call mvn -f D:/development/hallocasa-portal/HalloCasa/hallocasamig/pom.xml clean package resources:resources -U ^
 -Dwar.output.location=D:/development/hallocasa-environment/prod_target ^
 -Dglassfish.base=/usr/local/hallocasamig/api-server ^
 -Dglassfish.domain=hallocasa-mig ^
 -Dglassfish.admin.port=64646 ^
 -Dlogsdir=/usr/local/hallocasamig/logs-prod ^
 -Dglassfish.app.port=80 ^
 -Dmysql.jdbc=jdbc:mysql://localhost:3306/hallocasaappmig ^
 -Dmysql.pass=htGp64SK ^
 -Dfiles.propertyImages.rootPath=/usr/local/hallocasamig/property-images-prod ^
 -Dfiles.userImages.rootPath=/usr/local/hallocasamig/user-images-prod ^
 -Dfiles.propertyImages.miniPath=/usr/local/hallocasamig/property-images-prod/mini ^
 -Dfiles.userImages.miniPath=/usr/local/hallocasamig/user-images-prod/mini ^
 -Dapidoc.war.location=D:/development/hallocasa-environment/prod_target
cd D:/development/hallocasa-environment/prod_target
call del hallocasamig-endpoint-prod-1.1.0.war
call rename hallocasamig-endpoint-1.1.0.war hallocasamig-endpoint-prod-1.1.0.war
call copy /y D:\development\hallocasa-portal\HalloCasa\hallocasamig-endpoint\pom-local.xml D:\development\hallocasa-portal\HalloCasa\hallocasamig-endpoint\pom.xml
pause