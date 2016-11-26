copy D:/development/hc/hallocasa-portal/hallocasa-endpoint/pom.xml D:/development/hc/hallocasa-portal/hallocasa-endpoint/pom-local.xml
copy D:/development/hc/hallocasa-portal/hallocasa-endpoint/pom-server.xml D:/development/hc/hallocasa-portal/hallocasa-endpoint/pom.xml
mvn -f D:/development/hc/hallocasa-portal/hallocasamig/pom.xml clean package resources:resources -U ^
 -Dwar.output.location=D:/development/hc/hallocasa-environment/qa_target  ^
 -Dglassfish.base=/usr/local/hallocasamig/api-server-qa ^
 -Dglassfish.domain=hallocasa-mig ^ 
 -Dglassfish.admin.port=64646 ^
 -Dlogsdir=usr/local/hallocasamig/logs-qa ^
 -Dglassfish.app.port=64647  ^
 -Dmysql.jdbc=jdbc:mysql://localhost:3307/hallocasaapp ^
 -Dmysql.pass=masterkey  ^
 -Dfiles.propertyImages.rootPath=/usr/local/hallocasamig/property-images-qa ^ 
 -Dfiles.userImages.rootPath=/usr/local/hallocasamig/user-images-qa ^
 -Dapidoc.war.location=D:/development/hc/hallocasa-environment/qa_target
copy D:/development/hc/hallocasa-portal/hallocasa-endpoint/pom-local.xml D:/development/hc/hallocasa-portal/hallocasa-endpoint/pom.xml