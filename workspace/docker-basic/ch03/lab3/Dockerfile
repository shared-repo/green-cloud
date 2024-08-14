FROM alpine

CMD ["/opt/tomcat/bin/catalina.sh", "run"]

RUN apk update  \
&& apk add openjdk17-jre

ENV JAVA_HOME="/usr/lib/jvm/java-17-openjdk"

WORKDIR /opt
COPY tomcat ./tomcat
COPY demoweb.war ./tomcat/webapps/