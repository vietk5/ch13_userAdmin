FROM tomcat:10.1-jdk17

COPY target/useradmin-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war