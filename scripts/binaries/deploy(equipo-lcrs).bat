call copy /y D:\development\hc\hallocasa-portal\hallocasamig-endpoint\pom.xml D:\development\hc\hallocasa-portal\hallocasamig-endpoint\pom-local.xml
call copy /y D:\development\hc\hallocasa-portal\hallocasamig-endpoint\pom-server.xml D:\development\hc\hallocasa-portal\hallocasamig-endpoint\pom.xml
SET JAVA_HOME=C:/Program Files/java/jdk1.8.0_60
call mvn -f D:/development/hc/hallocasa-portal/hallocasamig/pom.xml clean package resources:resources -U ^
 -Dwar.output.location=D:/development/hc/hallocasa-environment/qa_target ^
 -Dglassfish.base=/usr/local/hallocasamig/api-server-qa ^
 -Dglassfish.domain=hallocasa-mig -Dglassfish.admin.port=64646 ^
 -Dlogsdir=usr/local/hallocasamig/logs-qa ^
 -Dglassfish.app.port=64647 ^
 -Dmysql.jdbc=jdbc:mysql://localhost:3307/hallocasaapp ^
 -Dmysql.pass=masterkey ^
 -Dfiles.propertyImages.rootPath=/usr/local/hallocasamig/property-images-qa -Dfiles.userImages.rootPath=/usr/local/hallocasamig/user-images-qa ^
 -Dapidoc.war.location=D:/development/hc/hallocasa-environment/qa_target
call copy /y D:\development\hc\hallocasa-portal\hallocasamig-endpoint\pom-local.xml D:\development\hc\hallocasa-portal\hallocasamig-endpoint\pom.xml
pause