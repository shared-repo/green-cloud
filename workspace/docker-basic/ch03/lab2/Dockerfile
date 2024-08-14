FROM alpine

CMD ["/opt/tomcat/bin/catalina.sh", "run"]

WORKDIR /opt

RUN apk update  \
&& apk add openjdk17-jre \
&& wget https://dlcdn.apache.org/tomcat/tomcat-10/v10.1.28/bin/apache-tomcat-10.1.28.tar.gz \
&& gunzip apache-tomcat-10.1.28.tar.gz \
&& tar xvf apache-tomcat-10.1.28.tar \
&& rm -f  apache-tomcat-10.1.28.tar \
&& mv apache-tomcat-10.1.28 tomcat

ENV JAVA_HOME="/usr/lib/jvm/java-17-openjdk"