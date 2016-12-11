# vaadinspringdemo

This is a small demo project to test Spring Boot and Vaadin. Uses also
- Java 8
- Spring Data
- Hibernate
- HikariCP

You need installed on your system:
- Java SE 8 JDK: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
- Apache Maven: https://maven.apache.org/download.cgi
- Postgresql: http://www.postgresql.org/download/

For postgresql, allow md5 authentication for local connections in pg_hba.conf

To compile and run Vaadinspringetcdemo:
(Linux commands are preceded by $-sign)

1. Prepare the database by evaluating init.sql using psql as appropriate database user. For example:
$ sudo su postgres -c 'psql -f src/main/resources/sql/init.sql'

2. build project using maven
$ mvn install

3. Run executable jar file
$ java -jar target/vaadinspringetcdemo-0.0.1-SNAPSHOT.jar

4. Open the web browser of your choise to address
http://localhost:8080
